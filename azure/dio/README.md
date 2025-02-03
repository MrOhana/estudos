## 1- AZURE WEBAPPS

### Instalar o az cli
- https://learn.microsoft.com/en-us/cli/azure/install-azure-cli

### Login na subscription da Azure
az login --tenant <TENANT_ID> (extraído da sua configuração de Tenant na Azure)

### Listando os resource groups
az group list --query "[].{id:name}" -o tsv

### Criando um resource group
az group create --location <myLocation> --name <myUniqueRGname>

### Criar o webapp 
az webapp up -g az-204-dio -n mrohanahtml01 --html

### Validar log 
az webapp log tail --name mrohanahtml01 --resource-group az-204-dio

### Buscando Web Apps
az webapp list --resource-group az-204-dio
az webapp list --resource-group az-204-dio --query "[?contains(name, 'mrohana')].{Name:name}" --output tsv 

### Acessa a pata da API e deploy a app
az webapp deployment source config-zip --resource-group az-204-dio --src api.zip --name imageapimrohana01

az webapp deployment source config-zip --resource-group az-204-dio --src web.zip --name imagewebmrohana01

### Auxiliar





## 2- AZURE FUNCTIONS

### Doc completa
- https://learn.microsoft.com/en-us/azure/azure-functions/

### Melhores práticas
- https://learn.microsoft.com/en-us/azure/azure-functions/functions-best-practices?tabs=csharp

### Instalar as ferramentas para executar as functions localmente
- https://learn.microsoft.com/en-us/azure/azure-functions/create-first-function-azure-developer-cli?pivots=programming-language-csharp&tabs=linux%2Cget%2Cbash%2Cpowershell

