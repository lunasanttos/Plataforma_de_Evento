package br.com.teste.service;
import br.com.teste.model.Certificado;
import br.com.teste.dao.CertificadoDao;

import java.sql.ResultSet;


public class CertificadoService {
    private CertificadoDao certificadoDao;

    public CertificadoService(){
        this.certificadoDao = new CertificadoDao(); // Sua versão: usa 'this' (melhor prática)
    }

    public ResultSet listar(){
        return  certificadoDao.listar();
    }

    public boolean inserir(Certificado certificado){

        if (!validar(certificado))
            return false;

        certificadoDao.inserir(certificado);
        return true;
    }

    public boolean excluir(Certificado certificado){

        if (certificado.getId_certificado() == 0) // Sua versão: usa getId_certificado() (correto conforme seu modelo)
            return false;

        certificadoDao.excluir(certificado);
        return true;
    }

    public boolean editar(Certificado certificado){

        if (!validar(certificado))
            return false;

        certificadoDao.editar(certificado);
        return true;
    }
    public boolean validar(Certificado certificado) {
        if (certificado.getData_emissao() == null || certificado.getCodigo_verificacao() == null)
            return false;

        if (certificado.getCodigo_verificacao().isEmpty())
            return false;

        return true;
    }
}