package com.itep.hust.aimsgroup.view.manager.view;
import com.itep.hust.aimsgroup.model.media.book.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
public class DetailBookViewHandler implements Initializable {
    @FXML
    private Label author;
    @FXML
    private Label publishDate;
    @FXML
    private Label publisher;
    @FXML
    private Label type;
    private Book book;

    public DetailBookViewHandler(Book book) {
        this.book = book;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        author.setText(book.getAuthor());
        type.setText(book.getCoverType());
        publisher.setText(book.getPublisher());
        publishDate.setText(book.getPublishDate().toString());
    }
}
