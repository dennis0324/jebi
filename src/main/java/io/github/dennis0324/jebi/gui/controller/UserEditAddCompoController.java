package io.github.dennis0324.jebi.gui.controller;

import java.util.Comparator;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.dennis0324.jebi.gui.TableViewHelper;
import io.github.dennis0324.jebi.model.Book;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class UserEditAddCompoController extends Controller {


    @FXML
    private MFXIconWrapper backBtn;

    @FXML
    private MFXPaginatedTableView<Book> table;


    @FXML
    void onBackBtnClicked(MouseEvent event) {
        //pageLoader로 들어옴
        PageLoader pageLoader = (PageLoader)getPageLoader();
        TableController tableController = (TableController)pageLoader.getArgument();
        pageLoader.to(tableController.getContentArea(),"/pages/Component/SearchComponent.fxml",tableController);
    }
    
    @SuppressWarnings("unchecked")
    public void initialize() {
        MaterialIconView icon = new MaterialIconView(MaterialIcon.CHEVRON_LEFT, "35"); // 'PERSON' is my icon from fontawesomefx, 22 is the icon size
        backBtn.setIcon(icon);
        backBtn.defaultRippleGeneratorBehavior();
        backBtn.getRippleGenerator().setRippleColor(Color.rgb(190, 190, 190));
        // NodeUtils.makeRegionCircular(sumbitButton);    

        MFXTableColumn<Book> nameColumn = new MFXTableColumn<>("책 이름",false,Comparator.comparing(Book::getName));
        MFXTableColumn<Book> authorColumn = new MFXTableColumn<>("작가",false,Comparator.comparing(Book::getAuthor));
        MFXTableColumn<Book> publisherColumn = new MFXTableColumn<>("출판사",false,Comparator.comparing(Book::getPublisher));

        nameColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(Book::getName,table,null,"/pages/Component/editbookComponent.fxml"));
        authorColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(Book::getAuthor,table,null,"/pages/Component/editbookComponent.fxml"));
        publisherColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(Book::getPublisher,table,null,"/pages/Component/editbookComponent.fxml"));
        
        table.getTableColumns().addAll(nameColumn,authorColumn,publisherColumn);

        table.getFilters().addAll(
            new StringFilter<>("책 이름",Book::getName),
            new StringFilter<>("작가",Book::getAuthor),
            new StringFilter<>("출판사",Book::getPublisher)
            );
            table.setItems(FXCollections.observableArrayList(new Book("testing")));
            table.setItems(FXCollections.observableArrayList(new Book("testing")));

    }

    @Override
    public void onPageLoad() {        
    }

}
