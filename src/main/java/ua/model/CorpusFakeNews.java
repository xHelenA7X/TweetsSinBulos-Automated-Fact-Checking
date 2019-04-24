package ua.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class CorpusFakeNews {
	private ArrayList<String[]> arrayDatosExcel;
	private List<KeywordsCorpus> keywords;
	
	public CorpusFakeNews() {
		arrayDatosExcel = readExcelFileToArray(new File("/etc/tweets/corpus/CorpusFakeNews.xls")); 
		keywords = new ArrayList<KeywordsCorpus>();
	}
	
	
	private int contarPalabras(String frase, String palabraBuscar) {
		StringTokenizer tok = new StringTokenizer(frase);
		int contador = 0;
		/*
		Bucle while que se repetirá mientras que existan tokens.
		*/
		while(tok.hasMoreTokens()) {
			if(tok.nextElement().equals(palabraBuscar)) {
				contador++;
			}
		}
		return contador;
	}

	private ArrayList<String[]> readExcelFileToArray(File excelFile){    
	    ArrayList<String[]> arrayDatos = new ArrayList<>();
	    InputStream excelStream = null;
	    try {
	        excelStream = new FileInputStream(excelFile);
	        // Representación del más alto nivel de la hoja excel.
	        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
	        // Elegimos la hoja que se pasa por parámetro.
	        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);    
	        // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
	        HSSFRow hssfRow = hssfSheet.getRow(hssfSheet.getTopRow());
	        String [] datos = new String[hssfRow.getLastCellNum()];            
	        // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos            
	        for (Row row: hssfSheet) {                    
	            for (Cell cell : row) {
	                /* 
	               	Tenemos estos tipos de celda
	                */
	                datos[cell.getColumnIndex()] =  
	                        (cell.getCellType() == Cell.CELL_TYPE_STRING)?cell.getStringCellValue():
	                        (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)?"" + cell.getNumericCellValue():
	                        (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)?"" + cell.getBooleanCellValue():
	                        (cell.getCellType() == Cell.CELL_TYPE_BLANK)?"BLANK":
	                        (cell.getCellType() == Cell.CELL_TYPE_FORMULA)?"FORMULA":
	                        (cell.getCellType() == Cell.CELL_TYPE_ERROR)?"ERROR":"";                                                                   
	            }
	            arrayDatos.add(datos); 
	            datos = new String[hssfRow.getLastCellNum()];  
	        }            
	    } catch (FileNotFoundException fileNotFoundException) {
	        System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
	    } catch (IOException ex) {
	        System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
	    } finally {
	        try {
	            excelStream.close();
	        } catch (IOException ex) {
	            System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
	        }
	    }
	    return arrayDatos;
    }
	 
	public void busquedaCorpusFakeNews(String palabra) {
        int fila = 0; //fila
        for (String[] next : arrayDatosExcel) {
        	//System.out.print("Array Row: " + r++ + " -> "); //Representa la fila
            String noticia = "";
            String titulo = "";
            //for (int c = 0; c < next.length; c++) {
                //System.out.print("[Column " + c + ": " + next[c] + "] "); //Representa la columna
            //}
            noticia = next[4];
            titulo = next[3];
            
            if(noticia.toLowerCase().contains(palabra) || titulo.toLowerCase().contains(palabra)) {
            	int numeroApariciones = contarPalabras(noticia.toLowerCase(), palabra);
            	KeywordsCorpus keyword = new KeywordsCorpus(palabra,fila,numeroApariciones);
            	/**
            	if(keywords.size()>0) {
            		boolean repetido = false;
            		int pos=-1;
            		for(int i = 0; i < keywords.size() && !repetido; i++) {
            			if(keyword.getFila() == keywords.get(i).getFila()) {
            				repetido=true;
            				pos=i;
            			}
            		}
            		if(repetido) { //Si la fila en cuestion ya se encuentra en el array, no la queremos duplicada, incrementamos el numero de apariciones y ya
            			keywords.get(pos).setNumeroApariciones(keywords.get(pos).getNumeroApariciones()+keyword.getNumeroApariciones());
            		}
            		else {
            			keywords.add(keyword);
            		}
            	}
            	else {
            		keywords.add(keyword);
            	}
            	**/
            	keywords.add(keyword);
            }
            fila++;
        }
	}
	
	public String getTextoNoticiaByFila(int fila) {
		String[] columnas = obtenerColumnasByFila(fila);
		return columnas[4];
	}
	
	public String getTituloByFila(int fila) {
		String[] columnas = obtenerColumnasByFila(fila);
		return columnas[3];
	}
	
	public String getVeracidadByFila(int fila) {
		String[] columnas = obtenerColumnasByFila(fila);
		return columnas[0];
	}
	
	public String getFuenteByFila(int fila) {
		String[] columnas = obtenerColumnasByFila(fila);
		return columnas[2];
	}
	
	public String getLinkByFila(int fila) {
		String[] columnas = obtenerColumnasByFila(fila);
		return columnas[5];
	}
	
	public String getTemaByFila(int fila) {
		String[] columnas = obtenerColumnasByFila(fila);
		return columnas[1];
	}
	
	public String[] obtenerColumnasByFila(int fila) {
		return arrayDatosExcel.get(fila);
	}
	
	public List<KeywordsCorpus> getKeywordsCorpus(){
		return keywords;
	}
	
}
