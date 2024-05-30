package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xlUtility = new XLUtility(path);

        int rowCount = xlUtility.getRowCount("Лист1");
        int colCount = xlUtility.getCellCount("Лист1", 1);

        String[][] data = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount ; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = xlUtility.getCellData("Лист1", i, j);
            }
        }
        return data;
    }

    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xlUtility = new XLUtility(path);

        int rowCount = xlUtility.getRowCount("Лист1");

        String[] names = new String[rowCount];

        for (int i = 1; i <= rowCount ; i++) {
            names[i - 1] = xlUtility.getCellData("Лист1", i, 1);
        }
        return names;
    }
}
