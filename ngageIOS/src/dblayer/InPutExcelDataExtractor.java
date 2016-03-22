package dblayer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import automationScript.OneToOneCurrentLocation;
import bean.FriendAddNameData;
import bean.FriendData;
import bean.GroupChatChooseExistingAudioData;
import bean.GroupChatData;
import bean.GroupChatDoodleData;
import bean.GroupChatExistingImageData;
import bean.GroupChatOtherLocationData;
import bean.GroupChatVideoRecordingData;
import bean.GroupCurrentLocationData;
import bean.GroupData;
import bean.GroupGreetingData;
import bean.GroupImageData;
import bean.GroupStickerData;
import bean.GroupTextData;
import bean.OneToOneAudioRecordingData;
import bean.OneToOneCurrentLocationData;
import bean.OneToOneData;
import bean.OneToOneDoodleData;
import bean.OneToOneExistingImageData;
import bean.OneToOneVideoRecordingData;
import bean.OneToOneGreetingData;
import bean.OneToOneImageData;
import bean.OneToOneOtherLocationData;
import bean.OneToOneStickerData;
import bean.OnetoOneChatData;
import bean.RegisterData;
import utilites.PropertyHandler;
/*@Author=Tapana
 *@Time=4:45pm 29Nov 2014 
 *@Descrption:In this calss callaction of excel sheet opertion ,one method copy one file to another file, 
 * setExcelStringData() method to send validate data to excel sheet,
 * setErrorMessage() method to send fail data and add coler in excel sheet
 * */
public class InPutExcelDataExtractor {
	//load logger file
	private static Logger Log = Logger.getLogger(InPutExcelDataExtractor.class.getName());
	//copy data to copy input excel  sheet to output excel sheet.
	public void copyData(){
		try {
			//load file of excel sheet.
			FileInputStream file = new FileInputStream(new File(PropertyHandler.getProperty("InPutDataFile")));
			//Get the workbook instance for XLS file 
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch(cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:

						break;
					case Cell.CELL_TYPE_NUMERIC:

						break;
					case Cell.CELL_TYPE_STRING:

						break;
					}
				}
			}
			file.close();
			FileOutputStream out =new FileOutputStream(new File(PropertyHandler.getProperty("OutPutDataFile")));
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			Log.info("exception in reading  data from list");
		} catch (IOException e) {
			Log.info("exception in reading  data from list");
		}
	}
	//set the data in excel sheet
	public void setExcelStringData(int sheetNo, int rowNum , int columeNum , String data) throws InvalidFormatException, IOException{
		//Property   file add
		//out put data set
		FileInputStream fis = new FileInputStream(PropertyHandler.getProperty("OutPutDataFile"));
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		Sheet sh = workbook.getSheetAt(sheetNo);
		Row r = sh.getRow(rowNum);
		Cell c = r.createCell(columeNum);
		c.setCellType(Cell.CELL_TYPE_STRING);
		c.setCellValue(data);
		//set the value in out put data(OutPutDataFile)
		FileOutputStream fos = new FileOutputStream(PropertyHandler.getProperty("OutPutDataFile"));
		workbook.write(fos);	 
	}
	public void setErrorMessage(int sheetNo, int rowNum , int columeNum , String data) throws IOException{
		// Create Workbook and Worksheet
		FileInputStream fis = new FileInputStream(PropertyHandler.getProperty("OutPutDataFile")); 
		HSSFWorkbook my_workbook = new HSSFWorkbook(fis);
		HSSFSheet my_sheet = my_workbook.getSheetAt(sheetNo);
		// Get access to HSSFCellStyl
		HSSFCellStyle my_style = my_workbook.createCellStyle();
		// We will now specify a background cell color */
		my_style.setFillPattern(HSSFCellStyle.FINE_DOTS );
		my_style.setFillForegroundColor(new HSSFColor.BLUE_GREY().getIndex());
		my_style.setFillBackgroundColor(new HSSFColor.PINK().getIndex());

		//Create a row in the sheet */
		Row row = my_sheet.getRow(rowNum);
		//Create a cell
		Cell cell = row.createCell(columeNum);
		cell.setCellValue(data);
		//Attach the style to the cell
		cell.setCellStyle(my_style);
		//Write changes to the workbook
		FileOutputStream out = new FileOutputStream(new File(PropertyHandler.getProperty("OutPutDataFile")));
		my_workbook.write(out);
	}
	/**
	 * method to load excel sheet data on input sheet number
	 * @param sheetNumber
	 * @return HSSFSheet object
	 */

	private HSSFSheet getHssfSheet(Integer sheetNumber) {
		HSSFSheet hssfSheet = null;	

		try {
			FileInputStream file = new FileInputStream(new File(PropertyHandler.getProperty("InPutDataFile")));
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file);
			hssfSheet = hssfWorkbook.getSheetAt(sheetNumber);
		} catch (FileNotFoundException e) {		
			Log.info("exception in reading  data from list");
		} catch (IOException e) {
			Log.info("exception in reading  data from list");
		}
		return hssfSheet;
	}


	public List<RegisterData> getResisterDataList(Integer sheetNumber)
	{		
		List<RegisterData> resisterDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				resisterDataList = new ArrayList<RegisterData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					RegisterData resisterData = new RegisterData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					resisterData.setUserName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String Mobile=(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					Mobile=Mobile.replace("_","");
					resisterData.setMobileNumber(Mobile);
					resisterData.setCountryName(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null );
					resisterDataList.add(resisterData);	
				}
			}				
		}catch(Exception e){
			Log.info("exception in reading  data from RegisterData ");
		}		
		return resisterDataList;
	}	

	public List<OnetoOneChatData> getOnetoOneChatDataList(Integer sheetNumber)
	{		
		List<OnetoOneChatData> onetoOneChatDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				onetoOneChatDataList = new ArrayList<OnetoOneChatData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OnetoOneChatData onetoOneChatData = new OnetoOneChatData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					onetoOneChatData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					onetoOneChatData.setMessageText(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null );
					onetoOneChatDataList.add(onetoOneChatData);	
				}
			}				
		}catch(Exception e){
			Log.info("exception in reading  data from RegisterData ");
		}		
		return onetoOneChatDataList;
	}	

	public List<GroupChatData> getGroupChatDataList(Integer sheetNumber)
	{		
		List<GroupChatData> groupChatDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupChatDataList = new ArrayList<GroupChatData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupChatData groupChatData = new GroupChatData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupChatData.setGroupMember(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					groupChatData.setExceptedName(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null );
					groupChatDataList.add(groupChatData);	
				}
			}				
		}catch(Exception e){
			Log.info("exception in reading  data from RegisterData ");
		}		
		return groupChatDataList;
	}

	public List<GroupData> getGroupDataList(Integer sheetNumber)
	{		
		List<GroupData> groupDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupDataList = new ArrayList<GroupData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupData groupData = new GroupData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupData.setGroupName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					groupDataList.add(groupData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from RegisterData ");
		}		
		return groupDataList;
	}
	public List<OneToOneData> getOneToOneTextList(Integer sheetNumber)
	{		
		List<OneToOneData> oneToOneTextList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				oneToOneTextList = new ArrayList<OneToOneData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OneToOneData oneToOneText = new OneToOneData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					oneToOneText.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					oneToOneText.setNoOfText(textNo);
					oneToOneText.setTextMessage(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					oneToOneText.setTypeOfText(row.getCell(3).getStringCellValue()!=null ? row.getCell(3).getStringCellValue() : null );
					oneToOneTextList.add(oneToOneText);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return oneToOneTextList;
	}
	public List<OneToOneImageData> getOneToOneImageDataList(Integer sheetNumber)
	{		
		List<OneToOneImageData> oneToOneImageDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				oneToOneImageDataList = new ArrayList<OneToOneImageData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OneToOneImageData oneToOneText = new OneToOneImageData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					oneToOneText.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					oneToOneText.setNoOfImage(textNo);
					oneToOneText.setTypeOFImage(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					oneToOneImageDataList.add(oneToOneText);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return oneToOneImageDataList;
	}
	public List<OneToOneGreetingData> getOneToOneGreetingDataList(Integer sheetNumber)
	{		
		List<OneToOneGreetingData> oneToOneGreetingDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				oneToOneGreetingDataList = new ArrayList<OneToOneGreetingData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OneToOneGreetingData oneToOneGreetingData = new OneToOneGreetingData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					oneToOneGreetingData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					oneToOneGreetingData.setNoOfGreeting(textNo);
					oneToOneGreetingData.setTypeOFGreeting(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					oneToOneGreetingDataList.add(oneToOneGreetingData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return oneToOneGreetingDataList;
	}
	
	public List<OneToOneStickerData> getOneToOneStickerDataList(Integer sheetNumber)
	{		
		List<OneToOneStickerData> oneToOneStickerDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				oneToOneStickerDataList = new ArrayList<OneToOneStickerData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OneToOneStickerData oneToOneStickerData = new OneToOneStickerData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					oneToOneStickerData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					oneToOneStickerData.setNoOfSticker(textNo);
					oneToOneStickerData.setTypeOFSticker(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					oneToOneStickerDataList.add(oneToOneStickerData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return oneToOneStickerDataList;
	}

	public List<OneToOneDoodleData> getOneToOneDoodleDataList(Integer sheetNumber)
	{		
		List<OneToOneDoodleData> oneToOneDoodleDatalist = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				oneToOneDoodleDatalist = new ArrayList<OneToOneDoodleData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OneToOneDoodleData oneToOneDoodleData = new OneToOneDoodleData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					oneToOneDoodleData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					oneToOneDoodleData.setNoOfDoodl(textNo);
					oneToOneDoodleData.setTypeOFDoodl(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					oneToOneDoodleDatalist.add(oneToOneDoodleData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return oneToOneDoodleDatalist;
	}
	
	public List<OneToOneCurrentLocationData> getOneToOneCurrentLocationList(Integer sheetNumber)
	{		
		List<OneToOneCurrentLocationData> oneToOneCurrentLocationList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				oneToOneCurrentLocationList = new ArrayList<OneToOneCurrentLocationData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OneToOneCurrentLocationData oneToOneCurrentLocationData = new OneToOneCurrentLocationData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					oneToOneCurrentLocationData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					oneToOneCurrentLocationData.setNoOfCurrentLocation(textNo);
					oneToOneCurrentLocationData.setTypeOfCurrentLOcation(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					oneToOneCurrentLocationList.add(oneToOneCurrentLocationData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return oneToOneCurrentLocationList;
	}
	public List<OneToOneOtherLocationData> getOneToOneOtherLocationDataList(Integer sheetNumber)
	{		
		List<OneToOneOtherLocationData> oneToOneOtherLocationDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				oneToOneOtherLocationDataList = new ArrayList<OneToOneOtherLocationData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OneToOneOtherLocationData oneToOneOtherLocationData = new OneToOneOtherLocationData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					oneToOneOtherLocationData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					oneToOneOtherLocationData.setNoOfOtherLocation(textNo);
					oneToOneOtherLocationData.setTypeOfOtherLOcation(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					oneToOneOtherLocationDataList.add(oneToOneOtherLocationData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return oneToOneOtherLocationDataList;
	}
	public List<OneToOneExistingImageData> getOneToOneExistingImageDataList(Integer sheetNumber)
	{		
		List<OneToOneExistingImageData> oneToOneExistingImageDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				oneToOneExistingImageDataList = new ArrayList<OneToOneExistingImageData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OneToOneExistingImageData oneToOneExistingImageData = new OneToOneExistingImageData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					oneToOneExistingImageData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					oneToOneExistingImageData.setNoOfExistingImage(textNo);
					oneToOneExistingImageData.setTypeOfExistingImage(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					oneToOneExistingImageDataList.add(oneToOneExistingImageData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return oneToOneExistingImageDataList;
	}
	public List<OneToOneVideoRecordingData> getOneToOneExistingVideoDataList(Integer sheetNumber)
	{		
		List<OneToOneVideoRecordingData> oneToOneExistingVideoDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				oneToOneExistingVideoDataList = new ArrayList<OneToOneVideoRecordingData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OneToOneVideoRecordingData oneToOneExistingVideoData = new OneToOneVideoRecordingData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					oneToOneExistingVideoData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					oneToOneExistingVideoData.setNoOfExistingVideo(textNo);
					oneToOneExistingVideoData.setTypeOfExistingVideo(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					oneToOneExistingVideoDataList.add(oneToOneExistingVideoData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return oneToOneExistingVideoDataList;
	}
	public List<OneToOneAudioRecordingData> getOneToOneAudioRecordingDataList(Integer sheetNumber)
	{		
		List<OneToOneAudioRecordingData> oneToOneAudioRecordingDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				oneToOneAudioRecordingDataList = new ArrayList<OneToOneAudioRecordingData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					OneToOneAudioRecordingData oneToOneAudioRecordingData = new OneToOneAudioRecordingData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					oneToOneAudioRecordingData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					oneToOneAudioRecordingData.setNoOfAudioRecording(textNo);
					oneToOneAudioRecordingData.setTypeOFAudioRecording(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					oneToOneAudioRecordingDataList.add(oneToOneAudioRecordingData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return oneToOneAudioRecordingDataList;
	}
	
	public List<GroupTextData> getGroupTextDataList(Integer sheetNumber)
	{		
		List<GroupTextData> groupTextDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupTextDataList = new ArrayList<GroupTextData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupTextData groupTextData = new GroupTextData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupTextData.setGroupName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					groupTextData.setNoOfTime(textNo);
					groupTextData.setTypeOf(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					groupTextData.setTextMessage(row.getCell(3).getStringCellValue()!=null ? row.getCell(3).getStringCellValue() : null );
					groupTextDataList.add(groupTextData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return groupTextDataList;
	}
	public List<GroupStickerData> getGroupStickerDataList(Integer sheetNumber)
	{		
		List<GroupStickerData> groupStickerDatalist = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupStickerDatalist = new ArrayList<GroupStickerData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupStickerData groupStickerData = new GroupStickerData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupStickerData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					groupStickerData.setNoOfSticker(textNo);
					groupStickerData.setTypeOFSticker(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					groupStickerDatalist.add(groupStickerData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return groupStickerDatalist;
	}
	public List<GroupImageData> getGroupImageDataList(Integer sheetNumber)
	{		
		List<GroupImageData> groupImageDatalist = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupImageDatalist = new ArrayList<GroupImageData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupImageData groupImageData = new GroupImageData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupImageData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					groupImageData.setNoOfImage(textNo);
					groupImageData.setTypeOFImage(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					groupImageDatalist.add(groupImageData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return groupImageDatalist;
	}
	public List<GroupGreetingData> getGroupGreetingDataList(Integer sheetNumber)
	{		
		List<GroupGreetingData> groupGreetingDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupGreetingDataList = new ArrayList<GroupGreetingData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupGreetingData groupGreetingData = new GroupGreetingData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupGreetingData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					groupGreetingData.setNoOfGreeting(textNo);
					groupGreetingData.setTypeOFGreeting(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					groupGreetingDataList.add(groupGreetingData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return groupGreetingDataList;
	}
	public List<GroupChatDoodleData> getGroupChatDoodleDataList(Integer sheetNumber)
	{		
		List<GroupChatDoodleData> groupChatDoodleDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupChatDoodleDataList = new ArrayList<GroupChatDoodleData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupChatDoodleData groupChatDoodleData = new GroupChatDoodleData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupChatDoodleData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					groupChatDoodleData.setNoOfDoodle(textNo);
					groupChatDoodleData.setTypeOFDoodle(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					groupChatDoodleDataList.add(groupChatDoodleData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return groupChatDoodleDataList;
	}
	public List<GroupCurrentLocationData> getGroupCurrentLocationDataList(Integer sheetNumber)
	{		
		List<GroupCurrentLocationData> groupCurrentLocationDatalist = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupCurrentLocationDatalist = new ArrayList<GroupCurrentLocationData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupCurrentLocationData groupCurrentLocationData = new GroupCurrentLocationData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupCurrentLocationData.setGroupName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					groupCurrentLocationData.setNoOfCurrentLocation(textNo);
					groupCurrentLocationData.setTypeOFCurrentLocation(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					groupCurrentLocationDatalist.add(groupCurrentLocationData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return groupCurrentLocationDatalist;
	}
	public List<GroupChatOtherLocationData> getGroupChatOtherLocationDataList(Integer sheetNumber)
	{		
		List<GroupChatOtherLocationData> groupChatOtherLocationDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupChatOtherLocationDataList = new ArrayList<GroupChatOtherLocationData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupChatOtherLocationData groupChatOtherLocationData = new GroupChatOtherLocationData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupChatOtherLocationData.setGroupName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					groupChatOtherLocationData.setNoOfOtherLocation(textNo);
					groupChatOtherLocationData.setTypeOFOtherLocation(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					groupChatOtherLocationDataList.add(groupChatOtherLocationData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return groupChatOtherLocationDataList;
	}
	public List<GroupChatExistingImageData> getGroupChatExistingImageDataList(Integer sheetNumber)
	{		
		List<GroupChatExistingImageData> groupChatExistingImageDatalist = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupChatExistingImageDatalist = new ArrayList<GroupChatExistingImageData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupChatExistingImageData groupChatExistingImageData = new GroupChatExistingImageData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupChatExistingImageData.setGroupName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					groupChatExistingImageData.setNoOfExistingImage(textNo);
					groupChatExistingImageData.setTypeOFExistingImage(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					groupChatExistingImageDatalist.add(groupChatExistingImageData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return groupChatExistingImageDatalist;
	}
	public List<GroupChatVideoRecordingData> getGroupChatVideoRecordingDataList(Integer sheetNumber)
	{		
		List<GroupChatVideoRecordingData> groupChatVideoRecordingDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupChatVideoRecordingDataList = new ArrayList<GroupChatVideoRecordingData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupChatVideoRecordingData groupChatVideoRecordingData = new GroupChatVideoRecordingData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupChatVideoRecordingData.setGroupName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					groupChatVideoRecordingData.setNoOFVideoRecording(textNo);
					groupChatVideoRecordingData.setTypeOFVideoRecording(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					groupChatVideoRecordingDataList.add(groupChatVideoRecordingData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return groupChatVideoRecordingDataList;
	}
	public List<GroupChatChooseExistingAudioData> getGroupChatChooseExistingAudioDataList(Integer sheetNumber)
	{		
		List<GroupChatChooseExistingAudioData> groupChatChooseExistingAudioDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				groupChatChooseExistingAudioDataList = new ArrayList<GroupChatChooseExistingAudioData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					GroupChatChooseExistingAudioData groupChatChooseExistingAudioData = new GroupChatChooseExistingAudioData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					groupChatChooseExistingAudioData.setGroupName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					String textNo=(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null);
					textNo=textNo.replace("_","");
					groupChatChooseExistingAudioData.setNoOFAudioRecording(textNo);
					groupChatChooseExistingAudioData.setTypeOFAudioRecording(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null );
					groupChatChooseExistingAudioDataList.add(groupChatChooseExistingAudioData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return groupChatChooseExistingAudioDataList;
	}
	
	public List<FriendAddNameData> getFriendAddNameDataList(Integer sheetNumber)
	{		
		List<FriendAddNameData> friendAddNameDataList = null;		

		try{			
			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}
			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0)
			{
				//colummn number starts with 1				
				friendAddNameDataList = new ArrayList<FriendAddNameData>();				
				//0th row contains column headings
				for (int i = 1; i <=totalRows; i++)
				{
					FriendAddNameData friendAddNameData = new FriendAddNameData();		
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in AddingcontactData class
					friendAddNameData.setFriendName(row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					friendAddNameData.setCountrySelection(row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null );
					String phoneNo=(row.getCell(2).getStringCellValue()!=null ? row.getCell(2).getStringCellValue() : null);
					phoneNo=phoneNo.replace("_","");
					friendAddNameData.setPhoneNumber(phoneNo);
					String conFirmPhoneNo=(row.getCell(3).getStringCellValue()!=null ? row.getCell(3).getStringCellValue() : null);
					conFirmPhoneNo=conFirmPhoneNo.replace("_","");
					friendAddNameData.setConfirmPhoneNumber(conFirmPhoneNo);
					friendAddNameData.setEmailId(row.getCell(4).getStringCellValue()!=null ? row.getCell(4).getStringCellValue() : null );
					friendAddNameDataList.add(friendAddNameData);	
				}
			}				
		}
		catch(Exception e)
		{
			Log.info("exception in reading  data from  getOneToOneTextList");
		}		
		return friendAddNameDataList;
	}
}
