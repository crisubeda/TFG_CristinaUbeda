% Nombre del archivo que contiene la lista de nombres de archivo
nombre_archivo = 'C:\Users\Cristina\Documents\IngenieriaBiomedica\4\TFG\FINAL2\PCGITA\PC_PYM\TEST\test_mlf.txt';

% Abre el archivo para leer
fid = fopen(nombre_archivo, 'r');

% Crea un archivo nuevo para escribir los resultados
resultado_archivo = 'C:\Users\Cristina\Documents\IngenieriaBiomedica\4\TFG\FINAL2\PCGITA\PC_PYM\TEST\test.mlf';
fid_resultado = fopen(resultado_archivo, 'w');

% Lee cada línea del archivo de entrada
tline = fgetl(fid);
while ischar(tline)
    % Verifica si el nombre del archivo contiene "pd" o "PD"
    if contains(lower(tline), 'pd')
        % Escribe el nombre del archivo y "PD" en la siguiente línea
        fprintf(fid_resultado, '"*/%s.lab"\nPD\n.\n', tline);
    % Verifica si el nombre del archivo contiene "hc" o "HC"
    elseif contains(lower(tline), 'hc')
        % Escribe el nombre del archivo y "HC" en la siguiente línea
        fprintf(fid_resultado, '"*/%s.lab"\nHC\n.\n', tline);
    end
    % Lee la siguiente línea del archivo
    tline = fgetl(fid);
end

% Cierra ambos archivos
fclose(fid);
fclose(fid_resultado);
