package com.yang.phone;

import com.yang.phone.service.sys.impl.SysUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneApplicationTests {

	@Test
	public void contextLoads() {
		SysUserServiceImpl userService=new SysUserServiceImpl();
	}

}

