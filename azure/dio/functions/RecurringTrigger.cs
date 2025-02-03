using System;
using Microsoft.Azure.Functions.Worker;
using Microsoft.Extensions.Logging;

namespace functions
{
    public class RecurringTrigger
    {
        private readonly ILogger _logger;

        public RecurringTrigger(ILoggerFactory loggerFactory)
        {
            _logger = loggerFactory.CreateLogger<RecurringTrigger>();
        }

        [Function("RecurringTrigger")]
        public void Run([TimerTrigger("0 */1 * * * *")] TimerInfo myTimer)
        {
            _logger.LogInformation($"C# Timer trigger function executed at: {DateTime.Now}");
            
            if (myTimer.ScheduleStatus is not null)
            {
                _logger.LogInformation($"Next timer schedule at: {myTimer.ScheduleStatus.Next}");
            }
        }
    }
}
