package com.tapioca.design.patterns;

/*
Interface Segregation Principle (ISP)
 */
class Document {

}

/*
You shouldn't put into your interface more than what the client is expected to implement.
Segregation means splitting it into parts.
 */
interface Machine {
    void print(Document d);

    void fax(Document d) throws Exception;

    void scan(Document d);
}

class MultiFunctionPrinter implements Machine {

    @Override
    public void print(Document d) {
        System.out.println("Print");
    }

    @Override
    public void fax(Document d) {
        System.out.println("Fax");
    }

    @Override
    public void scan(Document d) {
        System.out.println("Scan");
    }
}

class OldFashionedPrinter implements Machine {

    @Override
    public void print(Document d) {
        System.out.println("Print");
    }

    @Override
    public void fax(Document d) throws Exception {
        throw new Exception();
    }

    @Override
    public void scan(Document d) {
        System.out.println("Don't know scan");
    }
}

/*
A solution is to segregate their functionality.
 */
interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

/*
YAGNI - You Ain't Going to Need It
Just implement what you need.
 */
class JustAPrinter implements Printer {

    @Override
    public void print(Document d) {
        System.out.println("Scan");
    }
}

class PhotocopyMachine implements Printer, Scanner {

    @Override
    public void print(Document d) {
        System.out.println("Print");
    }

    @Override
    public void scan(Document d) {
        System.out.println("Scan");
    }
}

interface MultFunctionDevice extends Printer, Scanner {
}

class MultiFunctionMachine implements MultFunctionDevice {
    private Printer printer;
    private Scanner scanner;

    public MultiFunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}

public class ISP {

}
