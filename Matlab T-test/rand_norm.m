%
%
% generate n random normal samples with mean mu and covariance sigma
%
% these are put into sample where each row is a sample and columns
% are variables
%
% function sample = rand_norm(mu, sigma, n)
%

function samples = rand_norm(mu, sigma, n)

if nargin == 2
  n = 1
end

dim     = length(mu);
samples = zeros(n, dim, 1); 
cholc   = chol(sigma);

for i=1:n 
  
  samples(i,:,1) = cholc' * randn(dim,1) + mu; 
  
end; 

