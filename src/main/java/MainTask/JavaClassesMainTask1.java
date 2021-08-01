package MainTask;
import java.io.FileNotFoundException;
import java.io.FileReader;
import static java.lang.System.out;
import java.text.MessageFormat;
import java.util.Scanner;
class Student
{
    private final int id;
    private int course;
    int theNumberOfDay,month,theYearOfBirth;
    String surname,name,lastName,address,telephone,faculty,group;
    Student(int i)
    {
        id=i+1;
    }
    int getId()
    {
        return id;
    }
    int getCourse()
    {
        return course;
    }
    void setCourse(int i)
    {
        course =i;
    }
    boolean theDateIsCorrect()
    {
        boolean Continue=false;
        if (month>0 && month<13 && theYearOfBirth>1900 && theYearOfBirth<2100)
        {
            int[] theAmountOfDaysInEachMonth={31,28,31,30,31,30,31,31,30,31,30,31};
            if (theYearOfBirth%4==0)
            {
                theAmountOfDaysInEachMonth[1]+=1;
            }
            if (theNumberOfDay>0 && theNumberOfDay<=theAmountOfDaysInEachMonth[month-1])
            {
                Continue=true;
            }
        }
        return Continue;
    }
    void showInformation(int j)
    {
        String CurrentField="";
        switch (j)
        {
            case 1: { CurrentField=id+") "; break; }
            case 2: { CurrentField="фамилия: "+surname+", "; break; }
            case 3: { CurrentField="имя: "+name+", "; break; }
            case 4: { CurrentField="отчество: "+lastName+"; "; break; }
            case 5:
                {
                    CurrentField="дата рождения: ";
                    if (theNumberOfDay<10)
                    {
                        CurrentField+=0;
                    }
                    CurrentField+=theNumberOfDay+".";
                    break;
                }
            case 6:
                {
                    if (month<10)
                    {
                        CurrentField+=0;
                    }
                    CurrentField+=month;
                    break;
                }
            case 7: { CurrentField="."+theYearOfBirth+"; "; break; }
            case 8: { CurrentField="адрес: "+address+"; "; break; }
            case 9: { CurrentField="телефон: "+telephone+"; "; break; }
            case 10: { CurrentField="факультет: "+faculty+", "; break; }
            case 11: { CurrentField="курс: "+ course +", "; break; }
            case 12: { CurrentField="группа:"+group; break; }
            default:
                throw new IllegalStateException("Unexpected value: " + j);
        }
        out.print(CurrentField);
    }
}
public class JavaClassesMainTask1
{
    private static int n;
    private static Student[] students;
    private static String GivenFaculty,GivenGroup;
    private static int GivenYear;
    public static void main(String[] args) throws FileNotFoundException
    {
        GetInformationFromInput_txt();
        out.println("Список студентов:");
        for (int i=0; i<=n-2; i++)
        {
            for (int k=1; k<=12; k++)
            {
                students[i].showInformation(k);
            }
            out.println(";");
        }
        for (int k=1; k<=12; k++)
        {
            students[n-1].showInformation(k);
        }
        out.println(".");
        out.println();
        ListAorD(GivenFaculty);
        out.println();
        ListB();
        out.println();
        ListC();
        out.println();
        ListAorD(GivenGroup);
    }
    static void GetInformationFromInput_txt() throws FileNotFoundException
    {
        FileReader theFieldsOfClassStudent=new FileReader("Input.txt");
        Scanner input=new Scanner(theFieldsOfClassStudent);
        n=Integer.parseInt(input.nextLine());
        if (n<1)
        {
            System.exit(1);
        }
        students=new Student[n];
        for (int i=0; i<n; i++)
        {
            students[i]=new Student(i);
            students[i].surname=input.next();
            students[i].name=input.next();
            students[i].lastName=input.next();
            students[i].theNumberOfDay=Integer.parseInt(input.next());
            students[i].month=Integer.parseInt(input.next());
            students[i].theYearOfBirth=Integer.parseInt(input.next());
            if (!students[i].theDateIsCorrect())
            {
                System.out.print("The date of birth is not correct for student "+students[i].getId()+") "+students[i].surname+" "+students[i].name.charAt(0)+"."+students[i].lastName.charAt(0)+".");
                System.exit(1);
            }
            students[i].address=input.next();
            students[i].telephone=input.next();
            students[i].faculty=input.next();
            students[i].setCourse(input.nextInt());
            students[i].group=input.nextLine().trim();
        }
        GivenFaculty=input.nextLine();
        GivenYear=Integer.parseInt(input.nextLine());
        GivenGroup=input.nextLine();
    }
    static void ListAorD(String dataFromFile)
    {
        String[] currentData=new String[n];
        if (dataFromFile.equals(GivenFaculty))
        {
            out.print("a) ");
            for (int i=0; i<n; i++)
            {
                currentData[i]=students[i].faculty;
            }
        }
        if (dataFromFile.equals(GivenGroup))
        {
            out.print("d) ");
            for (int i=0; i<n; i++)
            {
                currentData[i]=students[i].group;
            }
        }
        int k=0;
        while (k<n && !dataFromFile.equals(currentData[k]))
        {
            k++;
        }
        if (k==n)
        {
            out.println("x");
        }
        else
        {
            out.println(students[k].getId()+") "+students[k].surname+" "+students[k].name.charAt(0)+"."+students[k].lastName.charAt(0)+".");
            for (int i=k+1; i<n; i++)
            {
                if (dataFromFile.equals(currentData[i]))
                {
                    out.println("   "+students[i].getId()+") "+students[i].surname+" "+students[i].name.charAt(0)+"."+students[i].lastName.charAt(0)+".");
                }
            }
        }
    }
    static void ListB()
    {
        out.print("b) ");
        int[] CourseNumbers=new int[n];
        int AmountOfCourses=0;
        for (int i=0; i<n; i++)
        {
            boolean NotFoundEquals=true;
            int memory=students[i].getCourse();
            for (int j=0; j<AmountOfCourses; j++)
            {
                if (memory==CourseNumbers[j])
                {
                    NotFoundEquals=false;
                    break;
                }
            }
            if (NotFoundEquals)
            {
                int m=0;
                while (CourseNumbers[m]<memory)
                {
                    if (m==AmountOfCourses)
                    {
                        break;
                    }
                    m++;
                }
                System.arraycopy(CourseNumbers,m,CourseNumbers,m+1,AmountOfCourses-m);
                CourseNumbers[m]=memory;
                AmountOfCourses++;
            }
        }
        out.print("///////");
        for (int t=0; t<AmountOfCourses; t++)
        {
            out.print("        "+CourseNumbers[t]);
        }
        out.println();
        int TheAmount=0;
        String[] theExistingFaculties=new String[n];
        int[][] Entries=new int[n][];
        int i=0;
        while (i<n)
        {
            int order=0;
            int[] members=new int[n];
            members[0]=i;
            theExistingFaculties[TheAmount]=students[i].faculty;
            for (int j=i+1; j<n; j++)
            {
                if (theExistingFaculties[TheAmount].equals(students[j].faculty))
                {
                    order++;
                    members[order]=j;
                }
            }
            Entries[TheAmount]=new int[order+1];
            System.arraycopy(members,0,Entries[TheAmount],0,order+1);
            int h=TheAmount;
            TheAmount++;
            while (h>=0)
            {
                if (students[i].faculty.equals(theExistingFaculties[h]))
                {
                    i++;
                    if (i==n)
                    {
                        break;
                    }
                    h=TheAmount;
                }
                h--;
            }
        }
        for (int u=0; u<TheAmount; u++)
        {
            out.print("  "+(u+1)+". "+theExistingFaculties[u]);
            for (int q=0; q<AmountOfCourses; q++)
            {
                int k=0;
                for (int c=0; c<Entries[u].length; c++)
                {
                    if (students[Entries[u][c]].getCourse()==CourseNumbers[q])
                    {
                        k++;
                        out.print("  "+(Entries[u][c]+1)+")");
                    }
                }
                if (k==0)
                {
                    out.print("  x");
                }
            }
            out.println();
        }
    }
    static void ListC()
    {
        out.print("c) ");
        int k=0;
        while (k<n && students[k].theYearOfBirth<=GivenYear)
        {
            k++;
        }
        if (k==n)
        {
            out.println("x");
        }
        else
        {
            out.println((k+1)+") "+students[k].surname+" "+students[k].name.charAt(0)+"."+students[k].lastName.charAt(0)+".");
            for (int h=k+1; h<n; h++)
            {
                if (students[h].theYearOfBirth>GivenYear)
                {
                    out.println("   "+(h+1)+") "+students[h].surname+" "+students[h].name.charAt(0)+"."+students[h].lastName.charAt(0)+".");
                }
            }
        }
    }
}