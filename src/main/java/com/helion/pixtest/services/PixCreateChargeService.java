package com.helion.pixtest.services;

import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.helion.pixtest.Credentials;

import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

@Service
public class PixCreateChargeService {
	
	public String pixCreateCharge() {
		
		Credentials credentials = new Credentials();

		  System.setProperty("javax.net.ssl.keyStore", credentials.getCertificadoPix());
	      JSONObject options = new JSONObject();
	      options.put("client_id", credentials.getClientId());
	      options.put("client_secret", credentials.getClientSecret());
	      options.put("pix_cert", credentials.getCertificadoPix());
	      options.put("sandbox", credentials.isSandbox());
	      final String uuid = UUID.randomUUID().toString().replace("-", "");
	     

	        HashMap<String, String> params = new HashMap<String, String>();
			    params.put("txid", uuid);

	        JSONObject body = new JSONObject();
	        body.put("calendario", new JSONObject().put("expiracao", 3600));
	        body.put("devedor", new JSONObject().put("cpf", "94271564656").put("nome", "Gorbadoc Oldbuck"));
	        body.put("valor", new JSONObject().put("original", "0.01"));
	        body.put("chave", "sua_chave");
	        body.put("solicitacaoPagador", "Serviço realizado.");

	        JSONArray infoAdicionais = new JSONArray();
	        infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
	        infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
	        body.put("infoAdicionais", infoAdicionais);

	        try {
	          Gerencianet gn = new Gerencianet(options);
	          JSONObject response = gn.call("pixCreateCharge", params, body);
	          System.out.println(response);
	          return response.toString();

	        }catch (GerencianetException e){
	          System.out.println(e.getError());
	          System.out.println(e.getErrorDescription());
	          return e.getErrorDescription();
	        }
	        catch (Exception e) {
	       
	          System.out.println(e.getMessage());
	          return e.getMessage();
	        }
	}
	
	
	public String pixCreateImediateCharge() {
		Credentials credentials = new Credentials();

	      JSONObject options = new JSONObject();
	      options.put("client_id", credentials.getClientId());
	      options.put("client_secret", credentials.getClientSecret());
	      options.put("pix_cert", credentials.getCertificadoPix());
	      options.put("sandbox", credentials.isSandbox());

	    
	        JSONObject body = new JSONObject();
	        body.put("calendario", new JSONObject().put("expiracao", 3600));
	        body.put("devedor", new JSONObject().put("cpf", "94271564656").put("nome", "Gorbadoc Oldbuck"));
	        body.put("valor", new JSONObject().put("original", "0.01"));
	        body.put("chave", "sua_chave");
	        body.put("solicitacaoPagador", "Serviço realizado.");

	        JSONArray infoAdicionais = new JSONArray();
	        infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
	        infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
	        body.put("infoAdicionais", infoAdicionais);
	        
	        
	        try {
	          Gerencianet gn = new Gerencianet(options);
	          JSONObject response = gn.call("pixCreateImmediateCharge", new HashMap<String,String>(), body);
	          System.out.println(response);
	          return response.toString();
	        }catch (GerencianetException e){
	          System.out.println(e.getError());
	          System.out.println(e.getErrorDescription());
	          
	          return e.getErrorDescription();
	        }
	        catch (Exception e) {
	          System.out.println(e.getMessage());
	          return e.getMessage();
	          
	        }
	}
}
