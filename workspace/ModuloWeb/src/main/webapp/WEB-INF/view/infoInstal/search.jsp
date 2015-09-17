<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!-- <div class="row emerge" -->
<!-- 	style="background-color: CornflowerBlue; min-height: 150px"> -->
<!-- 	<div id="cuadroBusqueda"> -->
<!-- 		<input class="form-control" type="text" ng-model="Busqueda" -->
<!-- 			name="Busqueda" value="" placeholder=""> <label -->
<!-- 			class="radio-inline"><input type="radio" ng-model="OptSearch" -->
<!-- 			name="OptSearch">eMail</label> <label class="radio-inline"><input -->
<!-- 			type="radio" ng-model="OptSearch" name="OptSearch">Teléfono</label> <a -->
<!-- 			href="#" class="btn btn-primary">Buscar...</a> -->
<!-- 	</div> -->
<!-- </div> -->
<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<h3 class="tituloSeccion">Búsqueda Instalación</h3>
		</div>
</div>
<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<hr class="tituloSeccion"/>
		</div>
</div>
<form method="post" name="searchForm">
	<div class="form-inline">
			<div class="enlinea width29" id="IE8W201"> <!-- class="col-md-3 col-sm-3 col-xs-3" -->
				<label><spring:message code="installation.search.installationnumber"/></label>
				<input class="form-control input-sm" type="text" value="" ng-model="searchBy.installationNumber" name="installationNumber">
			</div>
			<div class="enlinea width29" id="IE8W201">
				<label><spring:message code="installation.search.phone"/> </label>
				<input class="form-control input-sm" type="text" ng-model="searchBy.phone" pattern="[0-9]+" title="Sólo números" name="phone">
				<!--[if IE 8]>
					<span class="error errorAbsolute" ng-show="searchForm.phone.$error.pattern"><spring:message code="error.numeric"/></span>
				<![endif]--> 
			</div>
			<div class="enlinea width29" id="IE8W201">
				<label><spring:message code="installation.search.email"/> </label>
				<input class="form-control input-sm" type="email" value="" ng-model="searchBy.email" name="email">
				<!--[if IE 8]>
					<span class="error errorAbsolute" ng-show="searchForm.email.$error.email"><spring:message code="error.email"/></span>
				<![endif]-->	
			</div>
			<div class="enlinea width10" id="IE8W202">
				<button type="submit" class="btn btn-default btn-sm" title="<spring:message code="boton.search"/>" ng-click="searchForm.$valid ? searchInstallation() : null">
				    <span class="glyphicon glyphicon-search colorSearch" aria-hidden="true"></span>
	            </button>
			</div>
	</div>
</form>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" style="height: 50px;margin-top: 5px;">
        <table class="table table-bordered">
            <tr class="cabecillas">
<%--                 <th class="text-center"><spring:message code="actionplan.secuence"/></th> --%>
                <th class="text-center"><spring:message code="infoinstal.installationNumber"/></th>
                <th class="text-center" title='<spring:message code="infoinstal.emailMonitoring"/>'>EM</th>
                <th class="text-center" title='<spring:message code="infoinstal.emailBilling"/>'>EB</th>
                <th class="text-center" title='<spring:message code="installation.search.emailservices"/>'>ES</th>
                <th class="text-center" title='<spring:message code="actionplan.phone"/>'>TP</th>
                <th class="text-center" title='<spring:message code="installation.search.servicesphone"/>'>TS</th>
                <th class="text-center" title='<spring:message code="infoinstal.address"/>'><spring:message code="infoinstal.address"/></th>
            </tr>

 			<tr ng-repeat="i in searchedInstallations"> 
                <td>{{i.installationNumber}}</td>
                <td style="color:white;background-color: green;">{{i.emailMonitoring}}</td>
                <td><!-- {{ i.emailBilling }} --></td>
                <td>{{ i.emailServices }}</td>
                <td><!-- telefono del plan --></td>
                <td><!-- telefono de servicio --></td>
                <td>{{i.address+", "+i.city}}</td>
            </tr>
        </table>
    </div>
</div>