package br.com.wofsolutions.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class WofUtil {
	
	private static final int TAMANHO_BUFFER = 2048; // 2 Kb para o zip
	
	public static String md5(String senha) {
		String novaSenha = "";

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(senha.getBytes());
			BigInteger hash = new BigInteger(1, md.digest());
			novaSenha = hash.toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return novaSenha;
	}
	
	/**
	 * Coloca as primeiras letras de uma frase em maiusculo
	 * 
	 * @param palavra
	 * @return palara alterada
	 */
	public static String fcase(String palavra) {
		if (palavra != null) {
			int len = palavra.length();
			String out = "";
			out += palavra.substring(0, 1).toUpperCase();
			out += palavra.substring(1, len).toLowerCase();
			return out;
		}
		return palavra;
	}

	public String formataDecimal(float precoPromocao) {
		return new DecimalFormat("0.00").format(precoPromocao)
				.replace('.', ',');
	}

	public String extrairZip(File arquivoZip, File diretorio)
			throws ZipException, IOException {
		if (!diretorio.exists()) {
			diretorio.delete();
		}

		ZipFile zip = null;
		File arquivo = null;
		InputStream is = null;
		OutputStream os = null;
		byte[] buffer = new byte[TAMANHO_BUFFER];
		try {
			// cria diretÃ³rio informado, caso nÃ£o exista
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}
			if (!diretorio.exists() || !diretorio.isDirectory()) {
				throw new IOException("Informe um diretÃ³rio vÃ¡lido");
			}
			zip = new ZipFile(arquivoZip);
			Enumeration<?> e = zip.entries();
			while (e.hasMoreElements()) {
				ZipEntry entrada = (ZipEntry) e.nextElement();
				arquivo = new File(diretorio, entrada.getName());
				// se for diretÃ³rio inexistente, cria a estrutura
				// e pula pra prÃ³xima entrada
				if (entrada.isDirectory() && !arquivo.exists()) {
					arquivo.mkdirs();
					continue;
				}
				// se a estrutura de diretÃ³rios nÃ£o existe, cria
				if (!arquivo.getParentFile().exists()) {
					arquivo.getParentFile().mkdirs();
				}
				try {
					// lÃª o arquivo do zip e grava em disco
					is = zip.getInputStream(entrada);
					os = new FileOutputStream(arquivo);
					int bytesLidos = 0;
					if (is == null) {
						throw new ZipException("Erro ao ler a entrada do zip: "
								+ entrada.getName());
					}
					while ((bytesLidos = is.read(buffer)) > 0) {
						os.write(buffer, 0, bytesLidos);
					}
				} finally {
					if (is != null) {
						try {
							is.close();
						} catch (Exception ex) {
						}
					}
					if (os != null) {
						try {
							os.close();
						} catch (Exception ex) {
						}
					}
				}
			}
		} finally {
			if (zip != null) {
				try {
					zip.close();
				} catch (Exception e) {
				}
			}
		}
		return zip.getName();
	}


}
