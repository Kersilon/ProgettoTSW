package planetGaming.Ordine;

import planetGaming.Indirizzo.IndirizzoBean;
import planetGaming.Indirizzo.IndirizzoDAO;
import planetGaming.Utente.UtenteBean;
import planetGaming.Utente.UtenteDAO;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.util.ArrayList;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

@WebServlet("/Fattura")
public class Fattura extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private PDDocument invc = new PDDocument();
	private OrdineBean ordine = null;
	private UtenteBean utente = null;
	private IndirizzoBean indirizzo = null;
	private ArrayList<String> ProductName=new ArrayList<>();
	private ArrayList<Double> ProductPrice=new ArrayList<>();
	private ArrayList<Integer> ProductQty=new ArrayList<>();
	private ArrayList<Double> ProductIva=new ArrayList<>();
	private double total = 0;
	private double tasse = 0;
	private double price = 0;
	private double iva = 0;
	Date OrderDate;
	private int CustId;
	private String CustName;
	private String CustAddress;
	private String CustAddressShipping;
	private String InvoiceTitle="PlanetGaming";
	private String SubTitle="Fattura";
	
	public Fattura() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO prendi id dell'ordine ps: prova dalla session
		OrdineDAO ordineDao = new OrdineDAO();
		UtenteDAO utenteDao = new UtenteDAO();
		IndirizzoDAO indirizzoDao = new IndirizzoDAO();
		
		try {
			//TODO controlla l'implementazione
			ordine = ordineDao.doRetrieveByKey(Integer.parseInt(request.getParameter("OrderId")));
			utente = utenteDao.doRetrieveByKey(ordine.getIdUtente());
			indirizzo = indirizzoDao.doRetrieveByKey(ordine.getIdIndirizzo());
		} catch (SQLException e) {
			//response.addHeader("res", "403");
			e.printStackTrace();
		}
		//crea pagina del pdf e la inserisce nel pdf
	    PDPage newpage = new PDPage();
	    invc.addPage(newpage);
		
		getdata();
		WriteInvoice();
	    //Save the PDF
	
		File f=new File(getServletContext().getRealPath("/")+"\\fatture\\"+ordine.getIdOrdine()+".pdf");
		if(f.exists())
			f.delete();
		invc.save(f);

		//System.out.println(getServletContext().getRealPath("/")+"\\fatture\\"+ordine.getIdOrdine()+".pdf");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fatture/"+ordine.getIdOrdine()+".pdf");
		dispatcher.forward(request, response);
		
	}
	  
	 //prende tutti i dati dell'utente
	//TODO sostituire con utenteBean e OrdineBean
	 private void getdata() {
		 CustId = utente.getCodiceUtente();
	     CustName = utente.getNome()+" "+utente.getCognome();
	     CustAddress = indirizzo.getVia();
	     CustAddressShipping= indirizzo.getVia();
	     OrderDate = ordine.getDataOrdine();
	     
	     //inserisce dati di tutti i proditti acquistati
	     //TODO sostituire con prodottoOrdine
	     for(prodottoOrdineBean prodotto: ordine.getProdottiOrdine()) {
		      ProductName.add(prodotto.getNomeVideogioco());
		      ProductPrice.add(prodotto.getPrezzoAcquisto());
		      ProductQty.add(prodotto.getQuantitaAcquisto());
		      ProductIva.add(prodotto.getIva());
		      tasse = tasse + (prodotto.getPrezzoAcquisto()/100*prodotto.getIva()*prodotto.getQuantitaAcquisto());
		      total = total + (prodotto.getPrezzoAcquisto()*prodotto.getQuantitaAcquisto());
	    }
	  }
	  
	private  void WriteInvoice() {
	    //get the page
	    PDPage mypage = invc.getPage(0);
	    try {
	      //Prepare Content Stream
	      PDPageContentStream cs = new PDPageContentStream(invc, mypage);
	      
	      //Writing Single Line text
	      //Writing the Invoice title
	      
	      //Specifica che deve iniziare a scrivere
	      cs.beginText();
	      //Specifica il font del testo
	      cs.setFont(PDType1Font.TIMES_ROMAN, 20);
	      //Specifica la posizione in pixel(x, y) di dove iniziare a scrivere
	      cs.newLineAtOffset(270, 750);
	      //scrivere il testo
	      cs.showText(InvoiceTitle);
	      //Specifica che deve terminare quì il testo
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 18);
	      cs.newLineAtOffset(270, 690);
	      cs.showText(SubTitle);
	      cs.endText();
	      
	      //Writing Multiple Lines
	      //writing the customer details
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.setLeading(20f);
	      
	      cs.newLineAtOffset(50, 610);
	      cs.showText("Nome Cliente: ");
	      
	      //fa andare a capo
	      cs.newLine();
	      cs.showText("Indirizzo di fatturazione: ");
	      cs.newLine();
	      cs.showText("Indirizzo di spedizione: ");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(230, 610);
	      cs.showText(CustName);
	      cs.newLine();
	      cs.showText(CustAddress);
	      cs.newLine();
	      cs.showText(CustAddressShipping);
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(390, 610);
	      cs.showText("Codice Cliente: ");
	      cs.newLine();
	      cs.showText("Data Documento: ");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(500, 610);
	      cs.showText(String.valueOf(CustId));
	      cs.newLine();
	      cs.showText(OrderDate.toString());
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(50, 540);
	      cs.showText("Nome Articolo");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(270, 540);
	      cs.showText("Prezzo Unitario");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(370, 540);
	      cs.showText("Quantita'");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(440, 540);
	      cs.showText("Prezzo");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(500, 540);
	      cs.showText("IVA");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 9);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(50, 520);
	      for(int i =0; i<ordine.getProdottiOrdine().size(); i++) {
	    	if(ProductName.get(i).length()>50)
	    		
	    		//scrive al più "secondo valore di substring" caratteri
	    		cs.showText(ProductName.get(i).substring(0,50)+"...");
	    	else
	    		cs.showText(ProductName.get(i));
	        cs.newLine();
	      }
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 9);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(270, 520);
	      for(int i =0; i<ordine.getProdottiOrdine().size(); i++) {
	        cs.showText(ProductPrice.get(i).toString());
	        cs.newLine();
	      }
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 9);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(370, 520);
	      for(int i =0; i<ordine.getProdottiOrdine().size(); i++) {
	        cs.showText(ProductQty.get(i).toString());
	        cs.newLine();
	      }
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 9);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(440, 520);
	      for(int i =0; i<ordine.getProdottiOrdine().size(); i++) {
	        price = ProductPrice.get(i)*ProductQty.get(i);
	        cs.showText(String.valueOf(price));
	        cs.newLine();
	      }
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 9);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(500, 520);
	      for(int i =0; i<ordine.getProdottiOrdine().size(); i++) {
	        iva = ProductIva.get(i);
	        cs.showText(String.valueOf(iva));
	        cs.newLine();
	      }
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(310, (500-(20*ordine.getProdottiOrdine().size())));
	      cs.showText("Subtotale: ");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(310, (470-(20*ordine.getProdottiOrdine().size())));
	      cs.showText("Tasse: ");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(310, (430-(20*ordine.getProdottiOrdine().size())));
	      cs.showText("Totale: ");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      //Calculating where total is to be written using number of products
	      cs.newLineAtOffset(410, (500-(20*ordine.getProdottiOrdine().size())));
	      cs.showText(String.valueOf(total-tasse));
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      //Calculating where total is to be written using number of products
	      cs.newLineAtOffset(410, (470-(20*ordine.getProdottiOrdine().size())));
	      cs.showText(String.valueOf(tasse));
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      //Calculating where total is to be written using number of products
	      cs.newLineAtOffset(410, (430-(20*ordine.getProdottiOrdine().size())));
	      cs.showText(String.valueOf(total));
	      cs.endText();
	      
	      //Close the content stream
	      cs.close();
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	     
	    }
	    

	  }
  
  
  
   
}