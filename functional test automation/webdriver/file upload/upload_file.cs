// ===========================
// author: @dzhariy
// for @TIT upload collection
// ===========================
using System;
using System.IO;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

namespace CSFileUpload
{
    class Program
    {
        // Returns Absoulute full Uri from given system path
        public static string GetUriFromPath(string path)
        {
            Uri uri = new Uri(path);
            return uri.AbsoluteUri;
        }
        
        static void Main(string[] args)
        {
            // Compile project
            // Copy "index.html" and "lenna.png" into CSFileUpload.exe directory (bin\Debug\)
            // Run and enjoy!

            // создаём объект WebDriver для браузера FireFox
            var driver = new FirefoxDriver();

            // открываем страницу с формой загрузки файла
            var fileUri = GetUriFromPath(Path.Combine(Environment.CurrentDirectory, "index.html"));
            driver.Url = fileUri;

            // находим элемент <input type="file">
            var txtFileUpload = driver.FindElement(By.Id("file"));

            // заполняем элемент путём до загружаемого файла
            var sourceFile = Path.Combine(Environment.CurrentDirectory, "lenna.png");
            txtFileUpload.SendKeys(sourceFile);

            // находим элемент <input type="submit">
            var btnSubmit = driver.FindElement(By.Id("submit"));

            // нажимаем на элемент (отправляем форму)
            btnSubmit.Click();

            // закрываем браузер
            driver.Quit();
        }
    }
}
