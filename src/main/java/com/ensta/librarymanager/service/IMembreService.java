package com.ensta.librarymanager.service;

import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Membre;

public interface IMembreService {

	public List<Membre> getList() throws ServiceException;
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException;
	public Membre getById(int id) throws ServiceException;
	public Membre create(Membre membre) throws ServiceException;
	public void update(Membre membre) throws ServiceException;
	public void delete(int id) throws ServiceException;
	public int count() throws ServiceException;

}
