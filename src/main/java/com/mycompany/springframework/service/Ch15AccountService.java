package com.mycompany.springframework.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.springframework.dao.mybatis.Ch13AccountDao;
import com.mycompany.springframework.dto.Ch13Account;
import com.mycompany.springframework.exception.Ch15AccountNotExistException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch15AccountService {
	@Resource
	private Ch13AccountDao accountDao;
	
	public List<Ch13Account> getAccountList() {
		List<Ch13Account> list = accountDao.selectAll();
		return list;
	}
	
	public Ch13Account getAccount(int ano) {
		Ch13Account account = accountDao.selectByAno(ano);
		return account;
	}
	

	//@Transactional //입금계좌가 없다면 출금도 못하게 롤백시켜준다.
	public void transfer(int fromAno, int toAno, int amount) {
		//출금 작업
		Ch13Account fromAccount = accountDao.selectByAno(fromAno);
		if(fromAccount == null) {
			throw new Ch15AccountNotExistException("출금 계좌 없음");			
		}
		fromAccount.setBalance(fromAccount.getBalance()-amount);
		accountDao.update(fromAccount);
		
		//입금 작업
		Ch13Account toAccount = accountDao.selectByAno(toAno);
		if(toAccount == null) {
			throw new Ch15AccountNotExistException("입금 계좌 없음");			
		}
		toAccount.setBalance(toAccount.getBalance()+amount);
		accountDao.update(toAccount);
		
		
	}
	
	
	
}
