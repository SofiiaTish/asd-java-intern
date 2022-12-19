package team.asd.service;

import com.antkorwin.xsync.XSync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.ProductDao;
import team.asd.entity.Product;
import team.asd.exception.ValidationException;
import team.asd.util.ValidationUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class ThreadUpdateService {

	private final ProductDao threadUpdateDao;
	private ExecutorService executorService;

	private final XSync<Integer> xSync;

	public ThreadUpdateService(@Autowired ProductDao threadUpdateDao, XSync<Integer> xSync) {
		this.threadUpdateDao = threadUpdateDao;
		this.xSync = xSync;
		executorService = Executors.newFixedThreadPool(10);
	}

	public String threadUpdateProduct(Product product) {
		validProduct(product);
		executorService.execute(() -> xSync.execute(product.getId(), () -> threadUpdateDao.threadUpdateProduct(product)));
		return "Successful updating";
	}

	private void validProduct(Product product) {
		if (product == null) {
			throw new ValidationException("Product is null");
		}
		ValidationUtil.validId(product.getId());
	}
}
