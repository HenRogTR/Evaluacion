<table class="tabla_temporal">
	<tbody>
		<tr>
			<td><label>Escuelas</label></td>
			<td>
				<input type="text" name="txt_buscar" id="txt_buscar"
					placeholder="Nombre/Abreviatura" class="ancho3">
				<input type="button" name="btn_buscar" id="btn_buscar"
				value="Buscar">
			</td>
			<td><input type="button" name="btn_crear" id="btn_crear"
				value="Crear"></td>
		</tr>
	</tbody>
</table>
<br>
<table id="tabla_escuelaLista" class="tabla_temporal resaltar">
	<thead>
		<tr>
			<th class="ancho1_5">Nombre escuela</th>
			<th class="ancho1_5">Abreviatura escuela</th>
			<th class="ancho1_5">Nombre facultad</th>
			<th class="ancho1_5">Descripción</th>
			<th class="ancho2">Mas opciones</th>
		</tr>
	</thead>
	<tbody>
		
	</tbody>
</table>

<div id="dActualizar" title="Actualizar escuela" class="dialogoIniciar">
	<form id="form_actualizar" action="#">
		<table class="tabla_temporal">
			<tbody>
				<tr>
					<th class="ancho2">Id de escuela</th>
					<td class="ancho3">
						<span id="lbl_idEscuela_actualizar" 
							class="limpiar"></span>
						<input type="hidden" name="txt_idEscuela_actualizar" 
							id="txt_idEscuela_actualizar" class="limpiar">
					</td>
				</tr>
				<tr>
					<th>Id auxiliar</th>
					<td class="contenedorEntrada">
						<input type="text" name="txt_idEscuela2_actualizar" 
							id="txt_idEscuela2_actualizar" 
							class="anchoTotal limpiar">
					</td>
				</tr>
				<tr>
					<th>Nombre de escuela</th>
					<td class="contenedorEntrada">
						<textarea name="txt_nombreEscuela_actualizar" 
							id="txt_nombreEscuela_actualizar" 
							class="anchoTotal altoTotal limpiar"></textarea>
					</td>
				</tr>
				<tr>
					<th>Abreviatura de escuela</th>
					<td class="contenedorEntrada">
						<input type="text" name="txt_abreviaturaEscuela_actualizar"
							id="txt_abreviaturaEscuela_actualizar" 
							class="anchoTotal limpiar">
					</td>
				</tr>
				<tr>
					<th>Descripción</th>
					<td class="contenedorEntrada">
						<textarea name="txt_descripcion_actualizar" 
							id="txt_descripcion_actualizar" 
							class="anchoTotal altoTotal limpiar"></textarea>
					</td>
				</tr>
				<tr>
					<th>Facultad</th>
					<td class="contenedorEntrada">
						<select name="sel_idFacultad_actualizar" id="sel_idFacultad_actualizar" 
							class="anchoTotal limpiar">
							
						</select>
					</td>
				</tr>
			</tbody>
		</table>				
		<div class="div_error">
		</div>
	</form>
</div>

<div id="dCrear" title="Crear escuela" class="inicioOculto dialogoIniciar">
	<form id="form_crear" action="#">
		<table class="tabla_temporal">
			<tbody>
				<tr>
					<th class="ancho2">Id de escuela</th>
					<td class="ancho3">
						<span id="lbl_idEscuela_crear">Autogenerado</span>
					</td>
				</tr>
				<tr>
					<th>Id auxiliar</th>
					<td class="contenedorEntrada">
						<input type="text" name="txt_idEscuela2_crear" 
							id="txt_idEscuela2_crear" class="anchoTotal limpiar">
					</td>
				</tr>
				<tr>
					<th>Nombre de escuela</th>
					<td class="contenedorEntrada">
						<textarea name="txt_nombreEscuela_crear" 
							id="txt_nombreEscuela_crear" 
							class="anchoTotal altoTotal limpiar"></textarea>
					</td>
				</tr>
				<tr>
					<th>Abreviatura de escuela</th>
					<td class="contenedorEntrada">
						<input type="text" name="txt_abreviaturaEscuela_crear"
							id="txt_abreviaturaEscuela_crear" 
							class="anchoTotal limpiar">
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
				<tr>
					<th>Facultad</th>
					<td class="contenedorEntrada">
						<select name="sel_idFacultad_crear" id="sel_idFacultad_crear" 
							class="anchoTotal limpiar">
							
						</select>
					</td>
				</tr>
			</tbody>
		</table>		
		<div class="div_error">
		</div>
	</form>	
</div>