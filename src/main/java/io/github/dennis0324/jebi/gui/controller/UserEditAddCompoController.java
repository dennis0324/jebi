package io.github.dennis0324.jebi.gui.controller;

import java.util.Comparator;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.dennis0324.jebi.gui.TableViewHelper;
import io.github.dennis0324.jebi.gui.component.CapsuleButton;
import io.github.dennis0324.jebi.model.Book;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.dennis0324.jebi.gui.controller.UserEditAddCompoController.AddWindowType;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class UserEditAddCompoController extends Controller {

    public enum AddWindowType{
        add,
        edit
    }

    private AddWindowType addWindowType;
    
    @FXML
    private MFXIconWrapper backBtn;

    @FXML
    private MFXPaginatedTableView<Book> table;

    @FXML
    private HBox deleteBtnContainer;

    @FXML
    private MFXToggleButton editModeSelector;
    

    @FXML
    private MFXTextField email;

    @FXML
    private MFXTextField name;

    @FXML
    private MFXTextField phoneNumber;
    
    @FXML
    void onBackBtnClicked(MouseEvent event) {
        //다시 기본으로 돌아가는 버튼이다.
        
        //pageLoader로 들어옴
        PageLoader pageLoader = (PageLoader)getPageLoader();
        TableController tableController = (TableController)pageLoader.getArgument();
        pageLoader.to(tableController.getContentArea(),"/pages/Component/SearchComponent.fxml",tableController);
    }

    @FXML
    void onChangeEditMode(ActionEvent event) {
        System.out.println(editModeSelector.isSelected());
        email.setSelectable(editModeSelector.isSelected());
        email.setEditable(editModeSelector.isSelected());
        name.setSelectable(editModeSelector.isSelected());
        name.setEditable(editModeSelector.isSelected());
        phoneNumber.setSelectable(editModeSelector.isSelected());
        phoneNumber.setEditable(editModeSelector.isSelected());
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
        TableController tableController = (TableController)getPageLoader().getArgument();
        AddWindowType windowType = tableController.getWindowType();
        System.out.print(windowType);
        CapsuleButton button = new CapsuleButton();
        if(windowType == AddWindowType.add){
            table.setManaged(false);
            table.setVisible(false);
            button.getStylesheets().add(getClass().getResource("/css/customMFXbutton.css").toString());
            button.setText("Add");
            button.prefHeight(30);
            button.prefWidth(70);
        }
        else{
            button.getStylesheets().add(getClass().getResource("/css/customMFXButtonWarning.css").toString());
            button.setText("Delete");
            button.prefHeight(30);
            button.prefWidth(70);
        }
        deleteBtnContainer.getChildren().add(button);
    }

}
