package esalaf.projet11;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Date;
import java.sql.*;

public class DashboardController implements Initializable {
    @FXML
    private AnchorPane main_form;
    @FXML
    private Button available_addBtn;

    @FXML
    private TableColumn<ProductData, String> available_col_date;
    @FXML
    private TableColumn<ProductData, String> available_col_nameProduct;
    @FXML
    private TableColumn<ProductData, String> available_col_price;
    @FXML
    private TableColumn<ProductData, String> available_col_productID;
    @FXML
    private TableColumn<ProductData, String> available_col_status;
    @FXML
    private DatePicker available_date;
    @FXML
    private Button available_deleteBtn;
    @FXML
    private AnchorPane available_form;
    @FXML
    private TextField available_nameProduct;
    @FXML
    private TextField available_price;
    @FXML
    private TextField available_productID;
    @FXML
    private Button available_resetBtn;
    @FXML
    private TextField available_search;
    @FXML
    private ComboBox<?> available_status;
    @FXML
    private Button available_updateBtn;
    @FXML
    private Button availbleB_Btn;
    @FXML
    private ImageView close;
    @FXML
    private Button customer_btn;
    @FXML
    private AnchorPane customer_form;
    @FXML
    private TableColumn<CustomerData, String> customers_customerNum;
    @FXML
    private TableColumn<CustomerData, String> customers_date;
    @FXML
    private TableColumn<CustomerData, String> customers_firstName;
    @FXML
    private TableColumn<CustomerData, String> customers_gender;
    @FXML
    private TableColumn<CustomerData, String> customers_lastName;
    @FXML
    private TableColumn<CustomerData, String> customers_nameProduct;
    @FXML
    private TableColumn<CustomerData, String> customers_phoneNum;

    @FXML
    private TableColumn<CustomerData, String> customers_productID;
    @FXML
    private TextField customers_search;
    @FXML
    private TableView<CustomerData> customers_tableView;
    @FXML
    private TableColumn<CustomerData, String> customers_ticketNum;
    @FXML
    private TableColumn<CustomerData, String> customers_type;
    @FXML
    private Label dashboard_availble;
    @FXML
    private Button dashboard_btn;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private Label dashboard_incometoday;
    @FXML
    private Label dashboard_totalAmount;
    @FXML
    private Button logout;
    @FXML
    private Button minimize;
    @FXML
    private ComboBox<?> productSelect_ID;
    @FXML
    private DatePicker productSelect_date;
    @FXML
    private TextField productSelect_firstName;
    @FXML
    private AnchorPane productSelect_form;
    @FXML
    private ComboBox<?> productSelect_gender;
    @FXML
    private TextField productSelect_lastName;
    @FXML
    private ComboBox<?> productSelect_nameProduct;
    @FXML
    private TextField productSelect_phoneNum;
    @FXML
    private Button productSelect_resetBtn;
    @FXML
    private Label productSelect_sci_date;
    @FXML
    private Label productSelect_sci_firstName;
    @FXML
    private Label productSelect_sci_gender;
    @FXML
    private Label productSelect_sci_lastName;
    @FXML
    private Label productSelect_sci_nameProduct;
    @FXML
    private Button productSelect_sci_pay;
    @FXML
    private Label productSelect_sci_phone;
    @FXML
    private Label productSelect_sci_productID;
    @FXML
    private Button productSelect_sci_receipt;
    @FXML
    private Label productSelect_sci_ticket;
    @FXML
    private Label productSelect_sci_total;
    @FXML
    private Label productSelect_sci_type;
    @FXML
    private Button productSelect_selectBtn;
    @FXML
    private ComboBox<?> productSelect_quantity;
    @FXML
    private ComboBox<?> productSelect_type;
    @FXML
    private AreaChart<?, ?> dashboardchart;
    @FXML
    private Button projetSelect_Btn;
    @FXML
    private Label username;
    @FXML
    private TableView<ProductData> available_tableView;
    private double x=0;
    private  double y=0;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;


    public void availbleProductAdd(){

        String sql = "INSERT INTO product (product_id,name_product,status,price,date) VALUES (?,?,?,?,?)   ";
        connect = Database.connectDb();

        try {
            Alert alert;
            // CHECK IF THE FIELD ARE EMPTY
            if(available_productID.getText().isEmpty()
                    ||available_nameProduct.getText().isEmpty()
                    ||available_status.getSelectionModel().getSelectedItem()==null
                    ||available_price.getText().isEmpty()
                    ||available_date.getValue() == null )
            {
                alert = new Alert( Alert.AlertType.ERROR);
                alert.setTitle("Error Message ");
                alert.setHeaderText(null);
                alert.setContentText("Please fill All blanck fields ");
                alert.showAndWait();
            }
            else {
                // CHECK IF THE PRODODUCT IS ALREADY EXIST
                String check ="SELECT product_id FROM product WHERE product_id='"+available_productID.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if(result.next()){
                    alert = new Alert( Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product ID : "+available_productID.getText() +"was already exist!");
                    alert.showAndWait();
                }
                else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, available_productID.getText());
                    prepare.setString(2,available_nameProduct.getText());
                    prepare.setString(3, (String)available_status.getSelectionModel().getSelectedItem() );
                    prepare.setString(4,available_price.getText());
                    prepare.setString(5, String.valueOf(available_date.getValue()) );
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information MESSAGE ");
                    alert.setContentText("Successfullt Added");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW ONCE THE DATA IS SUCCEFFULY
                    availableBShowProductData();
                    availbleProductReset();

                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private  String[] statusList = {"Available","Not Available"};
    public void comBoxStatus(){
        ArrayList<String> listS = new ArrayList<>();

        for (String data:statusList){
            listS.add(data);
        }

        ObservableList listStatus = FXCollections.observableArrayList(listS);
        available_status.setItems(listStatus);


    }

    public void availbleProductReset(){
        available_productID.setText("");
        available_nameProduct.setText("");
        available_status.getSelectionModel().clearSelection();
        available_price.setText("");
        available_date.setValue(null);
    }

    public void availbleProductUpdate(){
        String sql = "UPDATE product SET name_product='"+available_nameProduct.getText()+"',status='"+available_status.getSelectionModel().getSelectedItem()+"',price='"+available_price.getText()+"',date='"+available_date.getValue()+"' WHERE product_id='"+available_productID.getText()+"'";
        connect = Database.connectDb();

        Alert alert;
        try {
            if(available_productID.getText().isEmpty()  // CHECK IF THE FIELD ARE EMPTY
                    ||available_nameProduct.getText().isEmpty()
                    ||available_status.getSelectionModel().getSelectedItem()==null
                    ||available_price.getText().isEmpty()
                    ||available_date.getValue() == null )
            {
                alert = new Alert( Alert.AlertType.ERROR);
                alert.setTitle("Error Message ");
                alert.setHeaderText(null);
                alert.setContentText("Please Select Item First ");
                alert.showAndWait();
            }
            else
            {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message ");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure youw want to UPDATE Product ID :  "+available_productID.getText() +" ?");

                Optional<ButtonType>  option = alert.showAndWait();
                if(option.get().equals(ButtonType.OK)) {

                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION Message ");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Update it");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW ONCE THE DATA IS SUCCEFFULY
                    availableBShowProductData();
                    availbleProductReset();

                }
                else {
                    return;
                }
            }

        }
        catch (Exception e)
         {
            e.printStackTrace();
         }
    }
    public void availbleProductDelete(){
        String sqlDelete = "DELETE FROM product WHERE product_id='"+available_productID.getText()+"'";
        connect = Database.connectDb();
        try {
            Alert alert;
            // CHECK IF THE FIELD ARE EMPTY
            if(available_productID.getText().isEmpty()
                    ||available_nameProduct.getText().isEmpty()
                    ||available_status.getSelectionModel().getSelectedItem()==null
                    ||available_price.getText().isEmpty()
                    ||available_date.getValue() == null )
            {
                alert = new Alert( Alert.AlertType.ERROR);
                alert.setTitle("Error Message ");
                alert.setHeaderText(null);
                alert.setContentText("Please fill All blanck fields ");
                alert.showAndWait();
            }
            else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Delete Product");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want Delete Product ID : "+available_productID.getText() +"?.");

                Optional<ButtonType> option = alert.showAndWait();
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sqlDelete);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW ONCE THE DATA IS SUCCEFFULY
                    availableBShowProductData();
                    availbleProductReset();

                }
                else  {
                    return ;
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
/*
    public void availbleProductSearch(){
        FilteredList<ProductData> filter = new FilteredList<>(availableBProductListData, e-> true) ;
        available_search.textProperty().addListener( (Observable, oldValue, newValue) -> {
            filter.setPredicate( predicateProductData -> {

                if(newValue.isEmpty() || newValue == null)
                 {
                        return true;
                 }
                String searchKey = newValue.toLowerCase();
                // Nothing? Then You need to do this -->

                if(predicateProductData.getProductId().toString().contains(searchKey) )
                 {
                    //IF INTEGER OR IF THE DATA TYPE IS NOT STRING, YOU MUST BE DO toString();
                        return true;
                 }
                else if( predicateProductData.getNameProduct().toLowerCase().contains(searchKey) )
                 {
                        return true;
                 }
                else if ( predicateProductData.getStatus().toLowerCase().contains(searchKey) )
                  {
                        return true;
                  }
                else if ( predicateProductData.getPrice().toString().contains(searchKey) )
                {
                    return true;
                }
                else if ( predicateProductData.getDate().toString().contains(searchKey)  )
                 {
                        return true;
                 }
                else
                  {
                      return false;
                  }
            } );
        } );

        SortedList<ProductData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(available_tableView.comparatorProperty());
        available_tableView.setItems(sortList);
    }
*/



    public ObservableList<ProductData> availbleProductData(){

        ObservableList<ProductData> productListData = FXCollections.observableArrayList();
        connect = Database.connectDb();
        String sql = "SELECT * FROM product";
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData productD;

            while (result.next()){
                productD = new ProductData(result.getInt("product_id"),result.getString("name_product"),result.getString("status"),result.getDouble("price"),result.getDate("date") );
                productListData.add(productD);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return productListData;
    }
    private ObservableList<ProductData> availableBProductListData ;
    public void availableBShowProductData(){
        availableBProductListData = availbleProductData();

        available_col_productID.setCellValueFactory( new PropertyValueFactory<>("productId"));
        available_col_nameProduct.setCellValueFactory( new PropertyValueFactory<>("nameProduct") );
        available_col_status.setCellValueFactory( new PropertyValueFactory<>("status") );
        available_col_price.setCellValueFactory( new PropertyValueFactory<>("price") );
        available_col_date.setCellValueFactory( new PropertyValueFactory<>("date") );

        available_tableView.setItems(availableBProductListData);

        FilteredList<ProductData> filter = new FilteredList<>(availableBProductListData, e-> true) ;
        available_search.textProperty().addListener( (Observable, oldValue, newValue) -> {
            filter.setPredicate( predicateProductData -> {

                if(newValue.isEmpty() || newValue == null)
                {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                // Nothing? Then You need to do this -->

                if(predicateProductData.getProductId().toString().indexOf(searchKey) > -1 )
                {
                    //IF INTEGER OR IF THE DATA TYPE IS NOT STRING, YOU MUST BE DO toString();
                    return true;
                }
                else if( predicateProductData.getNameProduct().toLowerCase().indexOf(searchKey) > -1 )
                {
                    return true;
                }
                else if ( predicateProductData.getStatus().toLowerCase().indexOf(searchKey) > -1 )
                {
                    return true;
                }
                else if ( predicateProductData.getPrice().toString().indexOf(searchKey) > -1 )
                {
                    return true;
                }
                else if ( predicateProductData.getDate().toString().indexOf(searchKey) > -1 )
                {
                    return true;
                }
                else
                {
                    return false;
                }
            } );
        } );

        SortedList<ProductData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(available_tableView.comparatorProperty());
        available_tableView.setItems(sortList);

    }

//----------------------------------------------------------------------------------------------------------------------

    public void productSIdList() {
        String productD = "SELECT * FROM product WHERE status = 'Available'";
        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(productD);
            result = prepare.executeQuery();

            ObservableList listP = FXCollections.observableArrayList();
            while (result.next()){
                listP.add(result.getString("product_id"));

            }
            productSelect_ID.setItems(listP);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void productSNameProductList(){
        String sql = "SELECT * FROM product WHERE status = 'Available'";

        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listN = FXCollections.observableArrayList();

            while (result.next()){
                listN.add(result.getString("name_product"));
            }
            productSelect_nameProduct.setItems(listN);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    // statusList
    public void productSTypeList(){
        List<String> tList = new ArrayList<>();

        for(String dataL : statusList ){
            tList.add(dataL);
        }

        ObservableList listType = FXCollections.observableArrayList(tList);
        productSelect_type.setItems(listType);
    }

    public void quantityList(){
        List<String> listQte = new ArrayList<>();
        for(int q = 1; q<=40; q++){
            listQte.add(String.valueOf(q));
        }
        ObservableList listQ = FXCollections.observableArrayList(listQte);
        productSelect_quantity.setItems(listQ);
    }
    private double totalP = 0;
    private double priceData = 0;
    public void productSelected(){
        String firstName = productSelect_firstName.getText();
        String lastName = productSelect_lastName.getText();
        String gender = (String) productSelect_gender.getSelectionModel().getSelectedItem();
        String phoneNumber = productSelect_phoneNum.getText();
        String date = String.valueOf(productSelect_date.getValue());

        String productID = (String)productSelect_ID.getSelectionModel().getSelectedItem();
        String productName = (String)productSelect_nameProduct.getSelectionModel().getSelectedItem();
        String type_product = (String)productSelect_type.getSelectionModel().getSelectedItem();
        String qte_product = (String)productSelect_quantity.getSelectionModel().getSelectedItem();
        int qte_products = Integer.parseInt(productSelect_quantity.getValue().toString());

        Alert alert;
        if (firstName == null || lastName == null || gender == null || phoneNumber == null || date == null || productID==null || productName==null ||type_product==null || qte_product==null ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }
        else {
            String totalPrice = "SELECT price FROM product WHERE name_product= '"+productName+"'";
            connect = Database.connectDb();

            try {

                prepare = connect.prepareStatement(totalPrice);
                result =prepare.executeQuery();

                if(result.next()){
                    priceData = result.getDouble("price");
                }
                if(type_product == "Available"){
                    totalP = priceData*qte_products;
                }

            }
            catch (Exception e){  e.printStackTrace(); }

            productSelect_sci_total.setText("$ " +String.valueOf(totalP ) );

            productSelect_sci_firstName.setText(firstName);
            productSelect_sci_lastName.setText(lastName);
            productSelect_sci_gender.setText(gender);
            productSelect_sci_phone.setText(phoneNumber);
            productSelect_sci_date.setText(date);
            productSelect_sci_productID.setText(productID);
            productSelect_sci_nameProduct.setText(productName);
            productSelect_sci_type.setText(type_product);
            productSelect_sci_ticket.setText(qte_product);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Selected!!!");
            alert.showAndWait();
            productSelectedReset();
        }
    }
    public void productSelectedReset(){
        productSelect_firstName.setText("");
        productSelect_lastName.setText("");
        productSelect_gender.getSelectionModel().clearSelection();
        productSelect_phoneNum.setText("");
        productSelect_date.setValue(null);
    }
    private  String[] genderL = {"Male","Female"};
    public void genderList(){
        List<String> listG = new ArrayList<>();
        for (String list : genderL){
            listG.add(list);
        }
        ObservableList gList = FXCollections.observableArrayList(listG);
        productSelect_gender.setItems(gList);
    }
    private int countRow;
    public void productSelectedPay(){

        String firstName = productSelect_sci_firstName.getText();
        String lastName = productSelect_sci_lastName.getText();
        String gender = productSelect_sci_gender.getText();
        String phoneNumber = productSelect_sci_phone.getText();
        String date = productSelect_sci_date.getText();

        String productID = (String)productSelect_sci_productID.getText();
        String productName = (String)productSelect_sci_nameProduct.getText();
        String type_product = (String)productSelect_sci_type.getText();
        String qte_product = (String)productSelect_sci_ticket.getText();

        int qte_products = Integer.parseInt(productSelect_quantity.getValue().toString());
        double pricee=0,totaloo=0;

            String totalPrice = "SELECT price FROM product WHERE name_product= '"+productName+"'";

            try {
                connect = Database.connectDb();
                prepare = connect.prepareStatement(totalPrice);
                result =prepare.executeQuery();

                if(result.next()){
                    pricee = result.getDouble("price");
                }
                if(type_product == "Available"){
                    totaloo = pricee*qte_products;
                }

            }
            catch (Exception e){  e.printStackTrace(); }

        String payData = "INSERT INTO customer(customer_id,first_name,last_name,gender,phoneNumber,productID,productName,type,quantity,total,date) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)  ";

        connect = Database.connectDb();
        try {
            String countNum = "SELECT COUNT(id) FROM customer  ";
            statement = connect.createStatement();
            result = statement.executeQuery(countNum);
            while (result.next()){
                countRow = result.getInt("COUNT(id)");
            }
            Alert alert;
            //CHECK IF EMPTY
            if(productSelect_sci_firstName.getText().isEmpty() || productSelect_sci_lastName.getText().isEmpty() || productSelect_sci_gender.getText().isEmpty() || productSelect_sci_phone.getText().isEmpty() || productSelect_sci_date.getText().isEmpty() || productSelect_sci_productID.getText().isEmpty() || productSelect_sci_nameProduct.getText().isEmpty() || productSelect_sci_type.getText().isEmpty() || productSelect_sci_ticket.getText().isEmpty() || totaloo==0  ){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else {
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Confirmation Message");
                alert2.setHeaderText(null);
                alert2.setContentText("Are you sure want to Paye IT ? ");

                Optional<ButtonType> option = alert2.showAndWait();

                if(option.get().equals(ButtonType.OK)){


                    prepare = connect.prepareStatement(payData);
                    prepare.setString(1,String.valueOf(countRow+1));
                    prepare.setString(2,firstName);
                    prepare.setString(3,lastName);
                    prepare.setString(4,gender);
                    prepare.setString(5,phoneNumber);
                    prepare.setString(6,productID);
                    prepare.setString(7,productName);
                    prepare.setString(8,type_product);
                    prepare.setString(9,qte_product);
                    prepare.setDouble(10,totaloo);
                    prepare.setString(11,date);

                    prepare.executeUpdate();

                    String receiptData = "INSERT INTO customer_receipt (customer_id,total,date) VALUES(?,?,?) ";
                    prepare = connect.prepareStatement(receiptData);
                    prepare.setString(1, String.valueOf(countRow+1));
                    prepare.setDouble(2, totaloo );
                    prepare.setString(3, date);
                    prepare.executeUpdate();

                    productSelectedPayReset();
                }
                else { return; }
            }
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void productSelectedPayReset(){
        productSelect_sci_firstName.setText("");
        productSelect_sci_lastName.setText("");
        productSelect_sci_gender.setText("");
        productSelect_sci_phone.setText("");
        productSelect_sci_date.setText("");
        productSelect_sci_productID.setText("");
        productSelect_sci_nameProduct.setText("");
        productSelect_sci_type.setText("");
        productSelect_sci_ticket.setText("");
        productSelect_sci_total.setText("$0.0");

    }

    //----------------------------------------------------------------------------------------------------------------------
    public ObservableList<CustomerData> customersDataList(){
        ObservableList<CustomerData> customerList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer ";
        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            CustomerData customerData;
            while (result.next()){
                customerData = new CustomerData(result.getInt("customer_id"),result.getString("first_name"),result.getString("last_name"),result.getString("gender"),result.getString("phoneNumber"),result.getInt("productID"),result.getString("productName"),result.getString("type"),result.getInt("quantity"),result.getDouble("total"),result.getDate("date"));
                customerList.add(customerData);
            }
        }
        catch (Exception e) { e.printStackTrace(); }

        return customerList;
    }
    private ObservableList<CustomerData> CustomerDataList ;
    public void customersShowDataList(){

        CustomerDataList = customersDataList();
        customers_customerNum.setCellValueFactory( new PropertyValueFactory<>("customerNum") );
        customers_ticketNum.setCellValueFactory( new PropertyValueFactory<>("quantity") );
        customers_firstName.setCellValueFactory( new PropertyValueFactory<>("first_name") );
        customers_lastName.setCellValueFactory( new PropertyValueFactory<>("last_name") );
        customers_gender.setCellValueFactory( new PropertyValueFactory<>("gender") );
        customers_phoneNum.setCellValueFactory( new PropertyValueFactory<>("phoneNumber") );
        customers_productID.setCellValueFactory( new PropertyValueFactory<>("productID") );
        customers_nameProduct.setCellValueFactory( new PropertyValueFactory<>("productName") );
        customers_type.setCellValueFactory( new PropertyValueFactory<>("type") );
        customers_date.setCellValueFactory( new PropertyValueFactory<>("date") );

        customers_tableView.setItems(CustomerDataList);

        //-------------------------
        FilteredList<CustomerData> filter = new FilteredList<>(CustomerDataList, e-> true );
        customers_search.textProperty().addListener( (Observable,oldValue,newValue) -> {
            filter.setPredicate(predicateCustomerData -> {
               //If no search value then display all records or whatever records it current have. no change
                if(newValue == null || newValue.isEmpty() || newValue.isBlank() ){
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if(predicateCustomerData.getCustomerNum().toString().indexOf(searchKey) > -1 ){
                    return true;  // Means we found a match in ProductName
                } else if (predicateCustomerData.getQuantity().toString().indexOf(searchKey)  > -1 ) {
                    return true;
                } else if (predicateCustomerData.getFirst_name().toLowerCase().indexOf(searchKey) > -1 ) {
                    return true;
                } else if (predicateCustomerData.getLast_name().toLowerCase().indexOf(searchKey) > -1 ) {
                    return true;
                } else if (predicateCustomerData.getGender().toLowerCase().indexOf(searchKey) > -1 ) {
                    return true;
                } else if (predicateCustomerData.getPhoneNumber().toLowerCase().indexOf(searchKey) > -1 ) {
                    return true;
                } else if (predicateCustomerData.getProductID().toString().indexOf(searchKey) > -1 ) {
                    return true;
                } else if (predicateCustomerData.getProductName().toLowerCase().indexOf(searchKey) > -1 ) {
                    return true;
                } else if (predicateCustomerData.getTotal().toString().indexOf(searchKey) > -1 ) {
                    return true;
                } else if (predicateCustomerData.getType().toLowerCase().indexOf(searchKey)  > -1 ) {
                    return true;
                } else if (predicateCustomerData.getDate().toString().indexOf(searchKey)  > -1 ) {
                    return true;
                } else {
                    return false;  // No match Found
                }
            });
        } );

        SortedList<CustomerData> sortedList = new SortedList<>(filter);

        // Bin sorted result with Table View
        sortedList.comparatorProperty().bind(customers_tableView.comparatorProperty());
        // Apply filtered and sorted data to the Table view
        customers_tableView.setItems(sortedList);
        //--------------
    }
/*
    public void customersSearch(){
        FilteredList<CustomerData> filter = new FilteredList<>(CustomerDataList, e-> true );
        customers_search.textProperty().addListener( (Observable,oldValue,newValue) -> {
            filter.setPredicate(predicateCustomerData -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if(predicateCustomerData.getCustomerNum().toString().contains(searchKey)){
                    return true;
                } else if (predicateCustomerData.getQuantity().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCustomerData.getFirst_name().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomerData.getLast_name().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomerData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomerData.getPhoneNumber().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomerData.getProductID().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCustomerData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomerData.getTotal().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCustomerData.getType().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomerData.getDate().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        } );

        SortedList<CustomerData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(customers_tableView.comparatorProperty());
        customers_tableView.setItems(sortedList);
    }
*/
    //------------------------ DASHBOARD ----------------------------------------------------------------------------------------------
    private int countAB =0;
    private double incomeToday=0;
    private double totalTA=0;
    public void dashboardDisplayAP(){
        String sql = "SELECT COUNT(id) FROM product WHERE status='Available'";
        connect = Database.connectDb();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                countAB = result.getInt("COUNT(id)");
            }
            dashboard_availble.setText( String.valueOf(countAB) );
        }
        catch (Exception e) {e.printStackTrace();}
    }
    public void dashboardDisplayIT(){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()) ;

        String sql = "SELECT SUM(total) FROM customer WHERE date='"+sqlDate+"'" ;

        connect = Database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                incomeToday = result.getDouble("SUM(total)");
            }
            dashboard_incometoday.setText( "$ "+String.valueOf(incomeToday) );
        }
        catch(Exception e) { e.printStackTrace(); }
    }
    public void dashboardDisplayTA(){
        String sql = "SELECT SUM(total) FROM customer ";
        connect = Database.connectDb();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                totalTA = result.getInt("SUM(total)");
            }
            dashboard_totalAmount.setText(  "$ "+ String.valueOf(totalTA) );
        }
        catch (Exception e) {e.printStackTrace();}
    }

    public void displayUsername(){
        username.setText(GetData.username);
    }

    public void dashboardChart(){
        dashboardchart.getData().clear();
        String sql = " SELECT date,SUM(total) FROM  customer WHERE date!='' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 9 ";
        connect = Database.connectDb();
        XYChart.Series chart = new XYChart.Series();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                chart.getData().add( new XYChart.Data(result.getString(1),result.getInt(2)) );
            }
            dashboardchart.getData().add(chart);

        }
        catch (Exception e) {e.printStackTrace();}
    }








    //----------------------------------------------------------------------------------------------------------------------





    public void availbleBSelectProductData(){
        ProductData productData = available_tableView.getSelectionModel().getSelectedItem();
        int num = available_tableView.getSelectionModel().getSelectedIndex();

        if( (num -1) < -1 ){
                return;
        }

        available_productID.setText( String.valueOf(productData.getProductId()) );
        available_nameProduct.setText( productData.getNameProduct() );
        available_price.setText(String.valueOf(productData.getPrice()) );
        available_date.setValue(LocalDate.parse(String.valueOf(productData.getDate()) ));

    }


    public void defaultBtn(){

        dashboard_btn.setStyle(" -fx-background-color: linear-gradient(to bottom right,#a73f4a, #3ea763 ) ");
        availbleB_Btn.setStyle(" -fx-background-color: transparent ");
        projetSelect_Btn.setStyle(" -fx-background-color: transparent ");
        customer_btn.setStyle(" -fx-background-color: transparent ");
    }

    public void switchForm(ActionEvent event ){
        if(event.getSource() == dashboard_btn ){
            dashboard_form.setVisible(true);
            available_form.setVisible(false);
            productSelect_form.setVisible(false);
            customer_form.setVisible(false);

            dashboard_btn.setStyle(" -fx-background-color: linear-gradient(to bottom right,#a73f4a, #3ea763 ) ");
            availbleB_Btn.setStyle(" -fx-background-color: transparent ");
            projetSelect_Btn.setStyle(" -fx-background-color: transparent ");
            customer_btn.setStyle(" -fx-background-color: transparent ");

            dashboardDisplayAP();
            dashboardDisplayIT();
            dashboardDisplayTA();
            displayUsername();
            dashboardChart();
        }
        else if (event.getSource() == availbleB_Btn ){

            dashboard_form.setVisible(false);
            available_form.setVisible(true);
            productSelect_form.setVisible(false);
            customer_form.setVisible(false);

            availbleB_Btn.setStyle(" -fx-background-color: linear-gradient(to bottom right,#a73f4a, #3ea763 ) ");
            customer_btn.setStyle(" -fx-background-color: transparent ");
            projetSelect_Btn.setStyle(" -fx-background-color: transparent ");
            dashboard_btn.setStyle(" -fx-background-color: transparent ");

         // TO UPDATE THE FORM  ONCE YOU CLICK THE AVAILBLE PRODUCT BUTTON
            availableBShowProductData();
           // availbleProductSearch();

        } else if (event.getSource() == projetSelect_Btn ) {
            dashboard_form.setVisible(false);
            available_form.setVisible(false);
            productSelect_form.setVisible(true);
            customer_form.setVisible(false);

            projetSelect_Btn.setStyle(" -fx-background-color: linear-gradient(to bottom right,#a73f4a, #3ea763 ) ");
            availbleB_Btn.setStyle(" -fx-background-color: transparent ");
            dashboard_btn.setStyle(" -fx-background-color: transparent ");
            customer_btn.setStyle(" -fx-background-color: transparent ");

            productSIdList();
            productSNameProductList();
            productSTypeList();
            quantityList();
            genderList();

        } else if (event.getSource() == customer_btn) {
            dashboard_form.setVisible(false);
            available_form.setVisible(false);
            productSelect_form.setVisible(false);
            customer_form.setVisible(true);

            customer_btn.setStyle(" -fx-background-color: linear-gradient(to bottom right,#a73f4a, #3ea763 ) ");
            availbleB_Btn.setStyle(" -fx-background-color: transparent ");
            projetSelect_Btn.setStyle(" -fx-background-color: transparent ");
            dashboard_btn.setStyle(" -fx-background-color: transparent ");

            customersShowDataList();
           // customersSearch();

        }
    }

    public void logout(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want to Logout? ");

            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){

                logout.getScene().getWindow().hide();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 842, 600);

                scene.setOnMousePressed( (MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();

                } );
                Stage stage = new Stage();

                scene.setOnMouseDragged( (MouseEvent event)->{
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                    stage.setOpacity(.8);
                } );

                scene.setOnMouseReleased( (MouseEvent event)-> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setTitle("Sign In !");
                stage.setScene(scene);
                stage.show();

            }
            else {
                return;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void close(){
        System.exit(0);
    }
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardDisplayAP();
        dashboardDisplayIT();
        dashboardDisplayTA();
        displayUsername();
        dashboardChart();

        defaultBtn();
        availableBShowProductData();
        comBoxStatus();

        productSIdList();
        productSNameProductList();
        productSTypeList();
        quantityList();
        genderList();
        customersShowDataList();


    }

}
