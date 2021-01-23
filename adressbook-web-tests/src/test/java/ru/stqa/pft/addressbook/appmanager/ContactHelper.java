package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.util.ArrayList;
import java.util.List;

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

    public void fillContactForm(ContactDate contactDate, boolean creation) {
      type(By.name("firstname"), contactDate.getFirstname());
        type(By.name("lastname"), contactDate.getLastname());
        type(By.name("email"), contactDate.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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
        //это можно будет использовать, если ассерты в подсчёте будут считать до загрузки
        //closeAlertWindow();
        //wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification(int id) {
        click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactDate contact, boolean b) {
        openNewContactForm();
        fillContactForm(contact, b);
        submitContactForm();
    }
    public void modify(List<ContactDate> before, int index, ContactDate contact) {
        selectContact(index);
        initContactModification((before.get(index).getId()));
        fillContactForm(contact, false);
        submitContactModification();
    }


    public void delete(int index) {
        selectContact(index);
        deleteSelectedContacts();
        closeAlertWindow();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
       return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactDate> list() {
        List<ContactDate> contacts = new ArrayList<ContactDate>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactDate().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}
