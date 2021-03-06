package com.mygaienko.trucking.controller;

import com.mygaienko.trucking.model.Contact;
import com.mygaienko.trucking.service.ContactService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by enda1n on 20.02.2016.
 */
public class MainController {

    // Инъекции Spring
    @Autowired
    private ContactService contactService;

    // Инъекции JavaFX
    @FXML private TableView<Contact> table;
    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;

    // Переменные
    private ObservableList<Contact> data;

    /**
     * Инициализация контроллера от JavaFX.
     * Метод вызывается после того как FXML загрузчик произвел инъекции полей.
     *
     * Обратите внимание, что имя метода <b>обязательно</b> должно быть "initialize",
     * в противном случае, метод не вызовется.
     *
     * Также на этом этапе еще отсутствуют бины спринга
     * и для инициализации лучше использовать метод,
     * описанный аннотацией @PostConstruct.
     * Который вызовется спрингом, после того,
     * как им будут произведены все оставшиеся инъекции.
     * {@link MainController#init()}
     */
    @FXML
    public void initialize() {
    }

    /**
     * На этом этапе уже произведены все возможные инъекции.
     */
    @PostConstruct
    public void init() {
        List<Contact> contacts = contactService.findAll();
        data = FXCollections.observableArrayList(contacts);

        // Добавляем столбцы к таблице
        TableColumn<Contact, String> idColumn = new TableColumn<Contact, String>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn<Contact, String> nameColumn = new TableColumn<Contact, String>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn<Contact, String> phoneColumn = new TableColumn<Contact, String>("Телефон");
        phoneColumn.setCellValueFactory(new PropertyValueFactory("phone"));

        TableColumn<Contact, String> emailColumn = new TableColumn<Contact, String>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));

        table.getColumns().setAll(idColumn, nameColumn, phoneColumn, emailColumn);

        // Добавляем данные в таблицу
        table.setItems(data);
    }

    /**
     * Метод, вызываемый при нажатии на кнопку "Добавить".
     * Привязан к кнопке в FXML файле представления.
     */
    @FXML
    public void addContact() {
        Contact contact = new Contact(txtName.getText(), txtPhone.getText(), txtEmail.getText());
        contactService.save(contact);
        data.add(contact);

        // чистим поля
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }
}
