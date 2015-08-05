/**
 * Controlador de la pestaña Facturación
 */
app.controller('invoicingController', function ($scope, $http, $log, CommonService, $modal,filterFilter) {

	//Query FieldConfig
	$scope.getFieldConfig=function(){
		$http(
			{
				method: 'GET',
				url: 'admin/getFieldConfig',
				params: {app: "FACT"}
			}
		).success(function (data, status, headers, config) {
				$scope.fieldConfig = data.data;
				CommonService.processBaseResponse(data,status,headers,config);
			})
			.error(function (data, status, headers, config) {
				CommonService.processBaseResponse(data,status,headers,config);
			});
	};
	
	//QUERY AUDIT
	$scope.getAudit=function(installationId){
		$log.debug("Query audit for ", installationId);
		$http(
			{
				method: 'GET',
				url: 'audit/getaudit',
				params: {installationId: installationId}
			}
		).success(function (data, status, headers, config) {
				$scope.audit = data.audit;
				CommonService.processBaseResponse(data,status,headers,config);
				$log.debug("Audit queried ", data.audit);
			})
			.error(function (data, status, headers, config) {
				CommonService.processBaseResponse(data,status,headers,config);
			});
		};
	
	//Método para obtener los datos de la factura
	$scope.getInvoice=function(installationNumber){ //¿¿ installation ID ???
		$log.debug("Query invoice for ", installationNumber);
		$http(
				{
					method: 'GET',
					url: 'invoice/getInvoice',
					params: {installationNumber: installationNumber}
				}
			).success(function (data, status, headers, config) {
					//Datos de información de facturación de la instalación	
					$scope.invoiceInfo = data.invoiceInfo;
					$scope.invoiceInfo.debtAmount=$scope.invoiceInfo.debtAmount+" €";	
					if ($scope.invoiceInfo.discount==true) {
						$scope.invoiceInfo.discount="Si";
					}else{
						$scope.invoiceInfo.discount="No";
					}
					
					//Datos de facturas cycleFeeds asociadas a la instalación
					$scope.cycleFeeds= data.cycleFeeds;
					
					//Listado de Facturas
					$scope.invoiceList=data.invoiceList;
					//Para la paginación;
					$scope.paginar("");
					
					CommonService.processBaseResponse(data,status,headers,config);
					$log.debug("invoiceInfo queried ", data.invoiceInfo);
					$log.debug("cycleFeeds queried ", data.cycleFeeds);
					$log.debug("invoiceList queried ", data.invoiceList);
				})
				.error(function (data, status, headers, config) {
					CommonService.processBaseResponse(data,status,headers,config);
				});
	};
	
	//Método de gestión del modal de detalle facturas
	$scope.openInvoiceDetailModal = function (size, item) {

		$scope.item={
				Amount:'997€',
				creationdate:'26-05-15',
				InvoiceNumber: 79824792,
				details:[{
				description: 'Descripción 1',
				period:'26-05-15',
				Amount:'271€',
				tax:21},
				{
				description: 'Descripción 2',
				period:'27-05-15',
				Amount:'26€',
				tax:21},
				{
				description: 'Descripción 3',
				period:'28-05-15',
				Amount:'652€',
				tax:21},
				{
				description: 'Descripción 4',
				period:'29-05-15',
				Amount:'48€',
				tax:21}]
		};
		var modalInstance = $modal.open({
			animation: $scope.animationsEnabled,
			templateUrl: 'invoiceDetailModalContent.html',
			controller: 'InvoiceDetailModalInstanceCtrl',
			size: size,
			resolve: {
				item: function () {
					return $scope.item;
				}
			}
		});

		modalInstance.result.then(function (selectedItem) {
			$scope.selected = selectedItem;
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};
	
	
	/**
	 * Paginación
	 *Al cambiar página asignaremos la posición del array correspondiente a la página
	 *Al cargar la página paginaremos con los filtros por defecto cargando la página 1
	 *Al cambiar el filtro modificaremos el array de datos y mostraremos la página 1
	 */
	
	$scope.pageChange=function(){
		$scope.paginaActual=$scope.paginas[$scope.bigCurrentPage-1];
		$log.info($scope.bigCurrentPage);
	}
	$scope.paginar=function(filtro){ //falta desarrollar el filtro de fechas
		var lista=filterFilter($scope.invoiceList, {'invoiceType':filtro});
		$scope.bigTotalItems=lista.length;
		$scope.bigCurrentPage=1;
		$scope.itemsPage=12;
		var numPags=Math.ceil($scope.bigTotalItems/$scope.itemsPage);
		$scope.paginas=new Array(numPags);
		var j=0;
		for (var i = 0; i<numPags; i++) {
			$scope.paginas[i]=new Array($scope.itemsPage);
			var hasta=j+$scope.itemsPage;
			for (j; j < hasta; j++) {
				$scope.paginas[i][j]=lista[j];
			}
		}
		$scope.paginaActual=$scope.paginas[0];
	}
	
	//Inicialización;
	$scope.getAudit(111111);
	$scope.getInvoice(971120);
	$scope.getFieldConfig();
	$scope.tipoFra=""; //Inicializamos filtro de tipo de factura para que muestre todas por defectos
});

//Controlador para gestionar el modal de detalle facturas
app.controller('InvoiceDetailModalInstanceCtrl', function ($scope, $modalInstance, item) {

	$scope.item = item;
//	$scope.selected = {
//			item: $scope.items[0]
//	};

	$scope.ok = function () {
		$modalInstance.close($scope.selected.item);
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
});