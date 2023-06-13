package com.jbk.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TypeMismatchException;
import org.hibernate.criterion.AggregateProjection;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.jbk.config.HibernateConfiguration;
import com.jbk.entity.Product;
import com.jbk.entity.ProductModel;
import com.jbk.utility.ProductUtility;

public class ProductDao {

	SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

	public String addProduct(Product product) {

		String msg = "";

		try {

			Session session = sessionFactory.openSession();
			session.save(product);
			session.beginTransaction().commit();

		} catch (Exception e) {
			System.out.println("Proudct With Id " + product.getProductId() + " is already present.");
		}

		return msg;
	}

	public Product getProducyById(Long productId) {
		Session session = sessionFactory.openSession();
		Product product = null;
		try {
			product = session.get(Product.class, productId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (product != null) {
				session.close();
			}
		}

		return product;
	}

	public String deleteProductById(Long productId) {

		String msg = null;

		Session session = sessionFactory.openSession();
		Product product = getProducyById(productId);

		try {
			if (product != null) {
				session.delete(product);
				session.beginTransaction().commit();
				msg = "Product deleted Succesfully with id " + productId;
			} else {
				msg = "Product Not Found With Id " + productId;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;
	}

	public String updateProduct(Product product) {

		Session session = null;
		String msg = null;

		try {
			session = sessionFactory.openSession();
			session.update(product);
			session.beginTransaction().commit();
			msg = "Product Updated Succesfully";

		} catch (OptimisticLockException e) {
			msg = "Product Not Found To Update With Id = " + product.getProductId();
		} catch (Exception e) {

			System.out.println("Something went wrong while updating the Product");

		} finally {
			if (session != null) {
				session.close();
			}

		}
		return msg;
	}

	public List<Product> getAllProducts() {

		Session session = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return list;

	}

	public List<Product> getProductBetweenAnyTwoIndex(int start, int End) {

		Session session = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setFirstResult(start);
			criteria.setMaxResults(End);
			list = criteria.list();
		}

		catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null)
				session.close();
		}

		return list;

	}

	public List<Product> sortProducts(String orderType, String propertyName) {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);

			if (orderType.equals("asc"))
				criteria.addOrder(Order.asc(propertyName));
			else
				criteria.addOrder(Order.desc(propertyName));

			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public List<Product> getProductsWithPropertyValue(String propertyName, Object value) {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.like(propertyName, value));
			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

	public List<Product> getProductWithPrice(double productPrice) {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.eq("productPrice", productPrice));
			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public List<Product> getProductWithPriceGreaterThan(double productPrice) {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.ge("productPrice", productPrice));
			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

	public List<Product> getProductWithPriceLessThan(double productPrice) {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.lt("productPrice", productPrice));
			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

	public List<Product> getProductsBetweenPriceRange(double low, double high) {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.between("productPrice", low, high));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Product> getProdcutsWithLowerPrice(Double productPrice) {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.lt("productPrice", productPrice));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public List<Product> getProductByCategoryId(Long categoryId1, Long categoryId2) {

		Session session = null;
		List<Product> list = null;
//		List<Product> list2 = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.ne("categoryId", categoryId1));
			criteria.add(Restrictions.ne("categoryId", categoryId2));
			list = criteria.list();
//			criteria.add(Restrictions.eqOrIsNull("categoryId", categoryId1));
//			list2=criteria.list();
//			if(list2 == null) {
//				System.out.println("Products Not Found with Category Id "+categoryId1);
//			}
//			list2=null;
//			criteria.add(Restrictions.eqOrIsNull("categoryId", categoryId2));
//			list2=criteria.list();
//			if(list2 == null) {
//				System.out.println("Products Not Found with Category Id "+categoryId2);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public List<Product> getProductByNameAndPrice(String productName, double productPrice) {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			Criterion criterion = Restrictions.like("productName", productName);
			Criterion criterion2 = Restrictions.eq("productPrice", productPrice);

			criteria.add(Restrictions.and(criterion, criterion2));

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Product> getProductByNameOrPrice(String productName, double productPrice) {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			Criterion criterion = Restrictions.like("productName", productName);
			Criterion criterion2 = Restrictions.eq("productPrice", productPrice);

			criteria.add(Restrictions.or(criterion, criterion2));

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Product> getMaxPriceProducts() {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.max("productPrice"));
			Double price = (Double) criteria.list().get(0);

			Criteria criteria2 = session.createCriteria(Product.class);
			criteria2.add(Restrictions.eq("productPrice", price));
			list = criteria2.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Product> getMinPriceProducts() {

		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.min("productPrice"));
			Double price = (Double) criteria.list().get(0);

			Criteria criteria2 = session.createCriteria(Product.class);
			criteria2.add(Restrictions.eq("productPrice", price));
			list = criteria2.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Product> getTotalNoOfProducts() {

		Session session = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
//			criteria.setProjection(Projections.count("productId"));
			criteria.setProjection(Projections.rowCount());
			list = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public List<Product> getTotalPriceOfElectronicsProducts() {

		Session session = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.sum("productQTY"));
//			criteria.setProjection(Projections.avg("productPrice"));
			list = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public List<ProductModel> queryExample() {

		Session session = null;

		List<ProductModel> list = new ArrayList<ProductModel>();

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			Query query = session.createQuery("select productName,productPrice FROM Product");
			List<Object[]> list2 = query.list();

			ProductModel model = null;

//		    Iterator iter = list2.iterator();
//		    while(iter.hasNext()){
//		      Object[] myResult = (Object[]) iter.next();
//
//		     model=new ProductModel(myResult[0], myResult[1]);	
//		      list.add(model);
//		    }

			for (Object[] obj : list2) {
				model = new ProductModel(obj[0], obj[1]);
				list.add(model);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

	public Product nativeQueryEx(Long productId) {

		Session session = null;

		List<Product> list = null;
		Product product = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			NativeQuery<Product> query = session.createNativeQuery("SELECT * from Product WHERE productId = :productId",
					Product.class);
			query.setParameter("productId", productId);
			product = query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return product;

	}

	public List<Product> getAllProducts2() {
		Session session = null;

		List<Product> list = new ArrayList<Product>();
		List<Object[]> obj = new ArrayList<Object[]>();

		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Product");
			list = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}



















//          Errors at starting 
/*
 * 1. We create the database we was facing the error of unknown database. 2. We
 * was facing the problem of unknown entity so we add //<property
 * name="hibernate.hbm2ddl.auto">create</property> propperty in .cfg.xml file.
 * 3. we face the same problem because session not identofy the class.So we
 * write .addAnnotatedClass(Product.class) in configuration file.
 * 
 * 4.
 * 
 */
