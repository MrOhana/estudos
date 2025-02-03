using System.Net;
using Microsoft.Azure.Functions.Worker;
using Microsoft.Azure.Functions.Worker.Http;
using Microsoft.Extensions.Logging;

namespace My.Function
{
    public class HttpExampleTrigger
    {
        private readonly ILogger<HttpExampleTrigger> _logger;

        public HttpExampleTrigger(ILogger<HttpExampleTrigger> logger)
        {
            _logger = logger;
        }

        [Function("HttpExampleTrigger")]
        public async Task<HttpResponseData> Run([HttpTrigger(AuthorizationLevel.Anonymous, "get", "post")] HttpRequestData req)
        {
            _logger.LogInformation("C# HTTP trigger function processed a request.");
            var response = req.CreateResponse(HttpStatusCode.OK);
            response.Headers.Add("Content-Type","text/plain; charset=utf-8");

            using (StreamReader reader = new StreamReader(req.Body))
            {   
                string name = await reader.ReadToEndAsync();
                await response.WriteStringAsync($"Hello, {name}");
            }

            return response;
        }
    }
}
