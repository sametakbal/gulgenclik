using System;
namespace gulgenclik.Models
{
	public class BuffetProduct: BaseEntity
	{
		public string? Title { get; set; }

        public decimal Price { get; set; }
    }
}

