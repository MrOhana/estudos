using System.Text.Json;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.Functions.Worker;
using Microsoft.Extensions.Logging;

namespace Mr.Ohana.Dio.Function
{
    public class MyJsonData
    {
        public string Cpf { get; set; }
    }

    public class ValidaCpfHttpTrigger
    {
        [Function("ValidaCpfHttpTrigger")]
        public static async Task<IActionResult> Run([HttpTrigger(AuthorizationLevel.Function, "post", Route = null)] HttpRequest req)
        {            
            Console.WriteLine("Testando CPF enviado...");
            var body = await new StreamReader(req.Body).ReadToEndAsync();

            MyJsonData? data = JsonSerializer.Deserialize<MyJsonData>(body);
            
            if (data == null) 
            {
                return new BadRequestObjectResult("Por favor, informe um CPF para ser validado.");
            }
            
            string cpf = data?.Cpf;

            Console.WriteLine($"CPF Testado: {cpf}");

            if (string.IsNullOrEmpty(cpf)) 
            {
                return new BadRequestObjectResult("Por favor, informe um CPF para ser validado.");
            }

            string msgResponse = "CPF inválido, verifique sua situação junto a Receita Federal";
            
            if (IsCpf(data?.Cpf))
            {
                msgResponse = $"O CPF {data?.Cpf} é válido";
            }

            return new OkObjectResult(msgResponse);
        }

        public static bool IsCpf(string cpf)
	    {
            int[] multiplicador1 = new int[9] { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
            int[] multiplicador2 = new int[10] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
            string tempCpf;
            string digito;
            int soma;
            int resto;

            cpf = cpf.Trim();
            cpf = cpf.Replace(".", "").Replace("-", "");
            
            if (cpf.Length != 11)
                return false;
            
            tempCpf = cpf.Substring(0, 9);
            soma = 0;

            for(int i=0; i<9; i++)
                soma += int.Parse(tempCpf[i].ToString()) * multiplicador1[i];
            
            resto = soma % 11;
            
            if ( resto < 2 )
                resto = 0;
            else
                resto = 11 - resto;
            
            digito = resto.ToString();
            tempCpf = tempCpf + digito;
            soma = 0;
            
            for(int i=0; i<10; i++)
                soma += int.Parse(tempCpf[i].ToString()) * multiplicador2[i];
            
            resto = soma % 11;
            if (resto < 2)
                resto = 0;
            else
                resto = 11 - resto;
            
            digito = digito + resto.ToString();
            
            return cpf.EndsWith(digito);
	      }
    }
}