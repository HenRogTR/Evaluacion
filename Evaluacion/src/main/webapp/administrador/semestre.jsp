<table class="tabla_temporal">
	<tbody>
		<tr>
			<td><label>Semestre</label></td>
			<td>
				<input type="text" name="txt_buscar" id="txt_buscar"
					placeholder="Semestre" class="ancho3">
				<input type="button" name="btn_buscar" id="btn_buscar"
				value="Buscar">
			</td>
			<td><input type="button" name="btn_crear" id="btn_crear"
				value="Crear"></td>
		</tr>
	</tbody>
</table>
<br>
<table id="tabla_semestreLista" class="tabla_temporal resaltar">
	<thead>
		<tr>
			<th class="ancho1_5">Semestre</th>
			<th class="ancho1_5">Fecha inicio</th>
			<th class="ancho1_5">Fecha fin</th>
			<th class="ancho2">Descripción</th>
			<th class="ancho2">Mas opciones</th>
		</tr>
	</thead>
	<tbody>
		
	</tbody>
</table>

<div id="div_semestre_crear" title="Registrar semestre">
	<form id="form_crear" action="SemestreServlet">
		<table class="tabla_temporal">		
			<tr>
				<th class="ancho1_5">Id auxiliar</th>
				<td class="ancho2 contenedorEntrada">
					<input type="text" name="txt_idSemestre2_crear" 
						id="txt_idSemestre2_crear" class="anchoTotal limpiar">
				</td>
			</tr>
			<tr>
				<th>Año</th>
				<td class="contenedorEntrada">
					<input type="text" id="txt_anio_crear" name="txt_anio_crear" 
						class="anchoTotal limpiar">
				</td>
			</tr>
			<tr>
				<th>Periodo</th>
				<td class="contenedorEntrada">
					<input type="text" id="txt_periodo_crear" 
						name="txt_periodo_crear" class="anchoTotal limpiar">
				</td>
			</tr>
			<tr>
				<th>Fecha inicio</th>
				<td class="contenedorEntrada">
					<input type="text" id="txt_fechaInicio_crear" 
						name="txt_fechaInicio_crear" 
						class="anchoTotal limpiar datepicker" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>Fecha fin</th>
				<td class="contenedorEntrada">
					<input type="text" id="txt_fechaFin_crear" 
						name="txt_fechaFin_crear" class="anchoTotal limpiar" 
						readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>Descripción</th>
				<td class="contenedorEntrada">
					<textarea name="txt_descripcion_crear" 
							id="txt_descripcion_crear" 
							class="anchoTotal altoTotal limpiar"></textarea>
				</td>
			</tr>
		</table>				
		<div class="div_error">
		</div>
	</form>
</div>