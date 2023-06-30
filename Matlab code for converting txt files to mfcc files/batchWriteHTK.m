function batchWriteHTK(directory, extension)
% directory es la ruta del directorio que contiene los archivos
% extension es la extensión de los archivos a procesar, por ejemplo, ".txt"

% Obtener la lista de archivos en el directorio
files = dir(fullfile(directory, ['*' extension]));

% Iterar a través de los archivos y aplicar la función writehtk a cada uno
for i = 1:length(files)
    % Obtener el nombre del archivo y cargar los datos
    filename = fullfile(directory, files(i).name);
    d = load(filename);
    fp = 0.002;
    tc = 9;
    % Aplicar la función writehtk y guardar el resultado en un archivo .htk
    [path, name, ~] = fileparts(filename);
    htkFilename = fullfile(path, [name '.fea']);
    writehtk2(htkFilename, d, fp, tc);
end
