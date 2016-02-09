<table class="tabla_temporal">
	<tbody>
		<tr>
			<td><label>Facultades</label></td>
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

<table id="tFacultadLista" class="tabla_temporal resaltar">
	<thead>
		<tr>
			<th class="ancho3">Nombre Facultad</th>
			<th class="ancho2">Abreviatura Facultad</th>
			<th class="ancho2">Descripción</th>
			<th class="ancho2">Mas opciones</th>
		</tr>
	</thead>
	<tbody>
		
	</tbody>
</table>

<div id="dFacultadActualizar" title="Actualizar facultad" class="inicioOculto dialogoIniciar">
	<form id="form_actualizar" action="#">
		<table class="tabla_temporal">
			<tbody>
				<tr>
					<th class="ancho2">Id de facultad</th>
					<td class="ancho3">
						<span id="lbl_idFacultad_actualizar" 
							class="limpiar"></span>
						<input type="hidden" name="txt_idFacultad_actualizar" 
							id="txt_idFacultad_actualizar" class="limpiar">
					</td>
				</tr>
				<tr>
					<th>Id auxiliar</th>
					<td class="contenedorEntrada">
						<input type="text" name="txt_idFacultad2_actualizar" 
							id="txt_idFacultad2_actualizar" 
							class="anchoTotal limpiar">
					</td>
				</tr>
				<tr>
					<th>Nombre de facultad</th>
					<td class="contenedorEntrada">
						<textarea name="txt_nombreFacultad_actualizar" 
							id="txt_nombreFacultad_actualizar" 
							class="anchoTotal altoTotal limpiar"></textarea>
					</td>
				</tr>
				<tr>
					<th>Abreviatura de facultad</th>
					<td class="contenedorEntrada">
						<input type="text" 
							name="txt_abreviaturaFacultad_actualizar"
							id="txt_abreviaturaFacultad_actualizar" 
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
			</tbody>
		</table>				
		<div class="div_error">
		</div>
	</form>
</div>

<div id="dFacultadCrear" title="Crear facultad" class="inicioOculto dialogoIniciar">
	<form id="form_crear" action="#">
		<table class="tabla_temporal">
			<tbody>
				<tr>
					<th class="ancho2">Id de facultad</th>
					<td class="ancho3">
						<span id="lbl_idFacultad_crear">Autogenerado</span>
					</td>
				</tr>
				<tr>
					<th>Id auxiliar</th>
					<td class="contenedorEntrada">
						<input type="text" name="txt_idFacultad2_crear" 
							id="txt_idFacultad2_crear" class="anchoTotal limpiar">
					</td>
				</tr>
				<tr>
					<th>Nombre de facultad</th>
					<td class="contenedorEntrada">
						<textarea name="txt_nombreFacultad_crear" 
							id="txt_nombreFacultad_crear" 
							class="anchoTotal altoTotal limpiar"></textarea>
					</td>
				</tr>
				<tr>
					<th>Abreviatura de facultad</th>
					<td class="contenedorEntrada">
						<input type="text" name="txt_abreviaturaFacultad_crear"
							id="txt_abreviaturaFacultad_crear" 
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
			</tbody>
		</table>		
		<div class="div_error">
		</div>
	</form>	
</div>

