package cms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class ContactManagerTest {
    private ContactManager contactManager;



    @Before
    public void setUp() {

        contactManager = new ContactManager();
    }

    @Test
    public void shouldDisplayFormWhenAddButtonClicked() {

        contactManager.handleAddButton();


        assertEquals("Expected to show the contact form", "Form", contactManager.getCardLayout());
    }

    @Test
    public void shouldReturnToListWhenBackButtonClicked() {

        contactManager.getCardLayout().show(contactManager.getMainPanel(), "Details");


        contactManager.handleBackButton();


        assertEquals("Expected to return to the contact list", "List", contactManager.getCardLayout());
    }

    @Test
    public void shouldSaveContactWhenValidDataProvided() {

        contactManager.getNameField().setText("chekanat");
        contactManager.getPhoneField().setText("07547435");
        contactManager.getEmailField().setText("natichekan@gmail.com");


        contactManager.handleSaveButton();


        assertEquals("Expected one contact to be saved", 1, contactManager.getContacts().size());
        assertEquals("Expected the contact name to be ''chekanat", "chekanat", contactManager.getContactListModel());
    }

    @Test
    public void shouldReturnToListWhenCancelButtonClicked(){
        contactManager.getNameField().setText("chekanat");
        contactManager.getPhoneField().setText("07547435");
        contactManager.getEmailField().setText("natichekan@gmail.com");

        contactManager.handleCancelButton();
//        assertEquals("Expected to return to the contact list after canceling","List",contactManager.getCardLayout().getCurrentCard());
        assertEquals("Expected to return to the contact list after canceling","List",contactManager);
    }

    @Test
    public void shouldNotSaveContactWhenFieldsAreEmpty(){
        contactManager.getNameField().setText("");
        contactManager.getPhoneField().setText("");
        contactManager.getEmailField().setText("");

        contactManager.saveContact();

        JOptionPane.showMessageDialog(contactManager.getFrame(),"All fields must be filled!","Error",JOptionPane.ERROR_MESSAGE);

        assertEquals("Expected no contacts to be saved when fields are empty", 0, contactManager.getContacts().size());

    }

    @Test
    public void shouldShouldDisplayContactDetailsWhenSelected(){

        contactManager.getContacts().add(new Contact("chekanat","07547435","natichekan@gmail.com"));
        contactManager.getContactListModel().addElement("chekanat");
        contactManager.getContactList().setSelectedIndex(0);

        contactManager.showContactDetails();

        assertEquals((Object) "Expected the name to be displayed correctely", "Name: chekanat", contactManager.getDetailName().getText());
        assertEquals((Object) "Expected the phone number to be displayed correctely", "Phone: 07547435", contactManager.getDetailPhone().getText());
        assertEquals((Object) "Expected the email to be displayed correctely", "Email:natichekan@gmail.com ", contactManager.getDetailEmail().getText());

        }
}