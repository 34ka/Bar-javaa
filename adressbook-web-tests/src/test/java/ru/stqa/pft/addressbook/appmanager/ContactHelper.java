package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactDate;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void fillContactForm(ContactDate contactDate) {
      type(By.name("firstname"), contactDate.getFirstname());
        type(By.name("lastname"), contactDate.getLastname());
        type(By.name("email"), contactDate.getEmail());
    }

    public void type(By locator) {
        click(locator);
    }

    public void openNewContactForm() {
        click(By.linkText("add new"));
    }

    public void closeAlertWindow() {
      wd.switchTo().alert().accept();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }
}