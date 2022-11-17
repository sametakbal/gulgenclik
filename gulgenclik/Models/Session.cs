using System;
namespace gulgenclik.Models
{
	public class Session : BaseEntity
	{
		public DateTime StartDate { get; set; } = DateTime.Now;

        public DateTime? EndDate { get; set; }

		public bool OnCredit { get; set; } = false;

	}
}

