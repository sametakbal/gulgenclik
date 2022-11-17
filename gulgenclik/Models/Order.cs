using System;
using System.Collections.ObjectModel;

namespace gulgenclik.Models
{
	public class Order : BaseEntity
	{
		public ICollection<BuffetProduct> BufferProducts { get; set; } = new Collection<BuffetProduct>();

		public decimal TotalPrice { get { return getTotalPrice(); } }

		public decimal getTotalPrice()
		{
			return BufferProducts.Sum(b => b.Price);
		}
		
	}
}

