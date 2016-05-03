package services;

import javax.inject.*;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

import nz.rafikn.datatables.request.Order;
import nz.rafikn.datatables.request.Request;
import nz.rafikn.datatables.response.DataTable;

@Singleton
public class DataService {

  // Process request here...
  public DataTable<User> getDataTable(Request dtRequest) {
    DataTable<User> result = new DataTable<User>();

    // Filtering
    List<User> users = filterUsers(dtRequest.getSearch().getValue());

    // Sorting
    users = sortUsers(users, dtRequest.getOrder().get(0));

    //  Pagination
    result.setData(users.subList(dtRequest.getStart(), java.lang.Math.min(users.size(), dtRequest.getStart() + dtRequest.getLength())));

    result.setDraw(dtRequest.getDraw());
    result.setRecordsTotal(getTotalUsers());
    result.setRecordsFiltered(users.size());

    return result;
  }


  // Filter users here...
  public List<User> filterUsers(String query) {
    if (query != null && !query.isEmpty() && query.length() >= 2) {
      return DataService.users
        .stream()
        .filter( u -> match(u, query.toLowerCase()))
        .collect(Collectors.toList());
    }
    return users;
  }

  private boolean match(User u, String query) {
    return u.first_name.toLowerCase().contains(query) ||
      u.last_name.toLowerCase().contains(query) ||
      u.position.toLowerCase().contains(query) ||
      u.office.toLowerCase().contains(query);
  }

  // order users here
  // Filter users here...
  public List<User> sortUsers(List<User> users, Order order) {
    // Columns Mapping
    Comparator<User> comparator = (u1, u2) -> 0;

    if (order.getDirection().equals(Order.Direction.ASC)) {
      switch(order.getColumn()) {
        case 0: // First name
          comparator = (u1, u2) -> u1.first_name.toLowerCase().compareTo(u2.first_name.toLowerCase());
          break;
        case 1: // Last name
        case 2: // Position
        case 4: // Office
        case 5: // Start date
        case 6: // Salary
        default:
          break;
      }
    } else if (order.getDirection().equals(Order.Direction.DESC)) {
      switch(order.getColumn()) {
        case 0: // First name
          comparator = (u1, u2) -> u2.first_name.toLowerCase().compareTo(u1.first_name.toLowerCase());
          break;
        case 1: // Last name
        case 2: // Position
        case 4: // Office
        case 5: // Start date
        case 6: // Salary
        default:
          break;
      }
    }

    return users.stream().sorted(comparator).collect(Collectors.toList());
  }

  public int getTotalUsers() {
    return DataService.users.size();
  }

  public static class User {
    String first_name;
    String last_name;
    String position;
    String office;
    String start_date;
    String salary;

    // getters & setters

    @Override
    public String toString() {
      return first_name + " " + last_name + " " + position + " " + office;
    }
  }

  /**
   * Acts as a datasource
   */
  private static final Gson gson = new Gson();
  private static List<User> users;
  private static String data;
  static {
    data = "[\n" +
    "    {\n" +
    "      \"first_name\": \"Airi\",\n" +
    "      \"last_name\": \"Satou\",\n" +
    "      \"position\": \"Accountant\",\n" +
    "      \"office\": \"Tokyo\",\n" +
    "      \"start_date\": \"28th Nov 08\",\n" +
    "      \"salary\": \"$162,700\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Angelica\",\n" +
    "      \"last_name\": \"Ramos\",\n" +
    "      \"position\": \"Chief Executive Officer (CEO)\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"9th Oct 09\",\n" +
    "      \"salary\": \"$1,200,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Ashton\",\n" +
    "      \"last_name\": \"Cox\",\n" +
    "      \"position\": \"Junior Technical Author\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"12th Jan 09\",\n" +
    "      \"salary\": \"$86,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Bradley\",\n" +
    "      \"last_name\": \"Greer\",\n" +
    "      \"position\": \"Software Engineer\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"13th Oct 12\",\n" +
    "      \"salary\": \"$132,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Brenden\",\n" +
    "      \"last_name\": \"Wagner\",\n" +
    "      \"position\": \"Software Engineer\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"7th Jun 11\",\n" +
    "      \"salary\": \"$206,850\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Brielle\",\n" +
    "      \"last_name\": \"Williamson\",\n" +
    "      \"position\": \"Integration Specialist\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"2nd Dec 12\",\n" +
    "      \"salary\": \"$372,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Bruno\",\n" +
    "      \"last_name\": \"Nash\",\n" +
    "      \"position\": \"Software Engineer\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"3rd May 11\",\n" +
    "      \"salary\": \"$163,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Caesar\",\n" +
    "      \"last_name\": \"Vance\",\n" +
    "      \"position\": \"Pre-Sales Support\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"12th Dec 11\",\n" +
    "      \"salary\": \"$106,450\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Cara\",\n" +
    "      \"last_name\": \"Stevens\",\n" +
    "      \"position\": \"Sales Assistant\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"6th Dec 11\",\n" +
    "      \"salary\": \"$145,600\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Cedric\",\n" +
    "      \"last_name\": \"Kelly\",\n" +
    "      \"position\": \"Senior Javascript Developer\",\n" +
    "      \"office\": \"Edinburgh\",\n" +
    "      \"start_date\": \"29th Mar 12\",\n" +
    "      \"salary\": \"$433,060\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Charde\",\n" +
    "      \"last_name\": \"Marshall\",\n" +
    "      \"position\": \"Regional Director\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"16th Oct 08\",\n" +
    "      \"salary\": \"$470,600\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Colleen\",\n" +
    "      \"last_name\": \"Hurst\",\n" +
    "      \"position\": \"Javascript Developer\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"15th Sep 09\",\n" +
    "      \"salary\": \"$205,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Dai\",\n" +
    "      \"last_name\": \"Rios\",\n" +
    "      \"position\": \"Personnel Lead\",\n" +
    "      \"office\": \"Edinburgh\",\n" +
    "      \"start_date\": \"26th Sep 12\",\n" +
    "      \"salary\": \"$217,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Donna\",\n" +
    "      \"last_name\": \"Snider\",\n" +
    "      \"position\": \"Customer Support\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"25th Jan 11\",\n" +
    "      \"salary\": \"$112,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Doris\",\n" +
    "      \"last_name\": \"Wilder\",\n" +
    "      \"position\": \"Sales Assistant\",\n" +
    "      \"office\": \"Sidney\",\n" +
    "      \"start_date\": \"20th Sep 10\",\n" +
    "      \"salary\": \"$85,600\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Finn\",\n" +
    "      \"last_name\": \"Camacho\",\n" +
    "      \"position\": \"Support Engineer\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"7th Jul 09\",\n" +
    "      \"salary\": \"$87,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Fiona\",\n" +
    "      \"last_name\": \"Green\",\n" +
    "      \"position\": \"Chief Operating Officer (COO)\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"11th Mar 10\",\n" +
    "      \"salary\": \"$850,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Garrett\",\n" +
    "      \"last_name\": \"Winters\",\n" +
    "      \"position\": \"Accountant\",\n" +
    "      \"office\": \"Tokyo\",\n" +
    "      \"start_date\": \"25th Jul 11\",\n" +
    "      \"salary\": \"$170,750\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Gavin\",\n" +
    "      \"last_name\": \"Joyce\",\n" +
    "      \"position\": \"Developer\",\n" +
    "      \"office\": \"Edinburgh\",\n" +
    "      \"start_date\": \"22nd Dec 10\",\n" +
    "      \"salary\": \"$92,575\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Gavin\",\n" +
    "      \"last_name\": \"Cortez\",\n" +
    "      \"position\": \"Team Leader\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"26th Oct 08\",\n" +
    "      \"salary\": \"$235,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Gloria\",\n" +
    "      \"last_name\": \"Little\",\n" +
    "      \"position\": \"Systems Administrator\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"10th Apr 09\",\n" +
    "      \"salary\": \"$237,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Haley\",\n" +
    "      \"last_name\": \"Kennedy\",\n" +
    "      \"position\": \"Senior Marketing Designer\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"18th Dec 12\",\n" +
    "      \"salary\": \"$313,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Hermione\",\n" +
    "      \"last_name\": \"Butler\",\n" +
    "      \"position\": \"Regional Director\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"21st Mar 11\",\n" +
    "      \"salary\": \"$356,250\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Herrod\",\n" +
    "      \"last_name\": \"Chandler\",\n" +
    "      \"position\": \"Sales Assistant\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"6th Aug 12\",\n" +
    "      \"salary\": \"$137,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Hope\",\n" +
    "      \"last_name\": \"Fuentes\",\n" +
    "      \"position\": \"Secretary\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"12th Feb 10\",\n" +
    "      \"salary\": \"$109,850\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Howard\",\n" +
    "      \"last_name\": \"Hatfield\",\n" +
    "      \"position\": \"Office Manager\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"16th Dec 08\",\n" +
    "      \"salary\": \"$164,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Jackson\",\n" +
    "      \"last_name\": \"Bradshaw\",\n" +
    "      \"position\": \"Director\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"26th Sep 08\",\n" +
    "      \"salary\": \"$645,750\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Jena\",\n" +
    "      \"last_name\": \"Gaines\",\n" +
    "      \"position\": \"Office Manager\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"19th Dec 08\",\n" +
    "      \"salary\": \"$90,560\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Jenette\",\n" +
    "      \"last_name\": \"Caldwell\",\n" +
    "      \"position\": \"Development Lead\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"3rd Sep 11\",\n" +
    "      \"salary\": \"$345,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Jennifer\",\n" +
    "      \"last_name\": \"Chang\",\n" +
    "      \"position\": \"Regional Director\",\n" +
    "      \"office\": \"Singapore\",\n" +
    "      \"start_date\": \"14th Nov 10\",\n" +
    "      \"salary\": \"$357,650\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Jennifer\",\n" +
    "      \"last_name\": \"Acosta\",\n" +
    "      \"position\": \"Junior Javascript Developer\",\n" +
    "      \"office\": \"Edinburgh\",\n" +
    "      \"start_date\": \"1st Feb 13\",\n" +
    "      \"salary\": \"$75,650\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Jonas\",\n" +
    "      \"last_name\": \"Alexander\",\n" +
    "      \"position\": \"Developer\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"14th Jul 10\",\n" +
    "      \"salary\": \"$86,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Lael\",\n" +
    "      \"last_name\": \"Greer\",\n" +
    "      \"position\": \"Systems Administrator\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"27th Feb 09\",\n" +
    "      \"salary\": \"$103,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Martena\",\n" +
    "      \"last_name\": \"Mccray\",\n" +
    "      \"position\": \"Post-Sales support\",\n" +
    "      \"office\": \"Edinburgh\",\n" +
    "      \"start_date\": \"9th Mar 11\",\n" +
    "      \"salary\": \"$324,050\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Michael\",\n" +
    "      \"last_name\": \"Silva\",\n" +
    "      \"position\": \"Marketing Designer\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"27th Nov 12\",\n" +
    "      \"salary\": \"$198,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Michael\",\n" +
    "      \"last_name\": \"Bruce\",\n" +
    "      \"position\": \"Javascript Developer\",\n" +
    "      \"office\": \"Singapore\",\n" +
    "      \"start_date\": \"27th Jun 11\",\n" +
    "      \"salary\": \"$183,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Michelle\",\n" +
    "      \"last_name\": \"House\",\n" +
    "      \"position\": \"Integration Specialist\",\n" +
    "      \"office\": \"Sidney\",\n" +
    "      \"start_date\": \"2nd Jun 11\",\n" +
    "      \"salary\": \"$95,400\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Olivia\",\n" +
    "      \"last_name\": \"Liang\",\n" +
    "      \"position\": \"Support Engineer\",\n" +
    "      \"office\": \"Singapore\",\n" +
    "      \"start_date\": \"3rd Feb 11\",\n" +
    "      \"salary\": \"$234,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Paul\",\n" +
    "      \"last_name\": \"Byrd\",\n" +
    "      \"position\": \"Chief Financial Officer (CFO)\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"9th Jun 10\",\n" +
    "      \"salary\": \"$725,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Prescott\",\n" +
    "      \"last_name\": \"Bartlett\",\n" +
    "      \"position\": \"Technical Author\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"7th May 11\",\n" +
    "      \"salary\": \"$145,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Quinn\",\n" +
    "      \"last_name\": \"Flynn\",\n" +
    "      \"position\": \"Support Lead\",\n" +
    "      \"office\": \"Edinburgh\",\n" +
    "      \"start_date\": \"3rd Mar 13\",\n" +
    "      \"salary\": \"$342,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Rhona\",\n" +
    "      \"last_name\": \"Davidson\",\n" +
    "      \"position\": \"Integration Specialist\",\n" +
    "      \"office\": \"Tokyo\",\n" +
    "      \"start_date\": \"14th Oct 10\",\n" +
    "      \"salary\": \"$327,900\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Sakura\",\n" +
    "      \"last_name\": \"Yamamoto\",\n" +
    "      \"position\": \"Support Engineer\",\n" +
    "      \"office\": \"Tokyo\",\n" +
    "      \"start_date\": \"19th Aug 09\",\n" +
    "      \"salary\": \"$139,575\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Serge\",\n" +
    "      \"last_name\": \"Baldwin\",\n" +
    "      \"position\": \"Data Coordinator\",\n" +
    "      \"office\": \"Singapore\",\n" +
    "      \"start_date\": \"9th Apr 12\",\n" +
    "      \"salary\": \"$138,575\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Shad\",\n" +
    "      \"last_name\": \"Decker\",\n" +
    "      \"position\": \"Regional Director\",\n" +
    "      \"office\": \"Edinburgh\",\n" +
    "      \"start_date\": \"13th Nov 08\",\n" +
    "      \"salary\": \"$183,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Shou\",\n" +
    "      \"last_name\": \"Itou\",\n" +
    "      \"position\": \"Regional Marketing\",\n" +
    "      \"office\": \"Tokyo\",\n" +
    "      \"start_date\": \"14th Aug 11\",\n" +
    "      \"salary\": \"$163,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Sonya\",\n" +
    "      \"last_name\": \"Frost\",\n" +
    "      \"position\": \"Software Engineer\",\n" +
    "      \"office\": \"Edinburgh\",\n" +
    "      \"start_date\": \"13th Dec 08\",\n" +
    "      \"salary\": \"$103,600\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Suki\",\n" +
    "      \"last_name\": \"Burks\",\n" +
    "      \"position\": \"Developer\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"22nd Oct 09\",\n" +
    "      \"salary\": \"$114,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Tatyana\",\n" +
    "      \"last_name\": \"Fitzpatrick\",\n" +
    "      \"position\": \"Regional Director\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"17th Mar 10\",\n" +
    "      \"salary\": \"$385,750\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Thor\",\n" +
    "      \"last_name\": \"Walton\",\n" +
    "      \"position\": \"Developer\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"11th Aug 13\",\n" +
    "      \"salary\": \"$98,540\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Tiger\",\n" +
    "      \"last_name\": \"Nixon\",\n" +
    "      \"position\": \"System Architect\",\n" +
    "      \"office\": \"Edinburgh\",\n" +
    "      \"start_date\": \"25th Apr 11\",\n" +
    "      \"salary\": \"$320,800\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Timothy\",\n" +
    "      \"last_name\": \"Mooney\",\n" +
    "      \"position\": \"Office Manager\",\n" +
    "      \"office\": \"London\",\n" +
    "      \"start_date\": \"11th Dec 08\",\n" +
    "      \"salary\": \"$136,200\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Unity\",\n" +
    "      \"last_name\": \"Butler\",\n" +
    "      \"position\": \"Marketing Designer\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"9th Dec 09\",\n" +
    "      \"salary\": \"$85,675\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Vivian\",\n" +
    "      \"last_name\": \"Harrell\",\n" +
    "      \"position\": \"Financial Controller\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"14th Feb 09\",\n" +
    "      \"salary\": \"$452,500\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Yuri\",\n" +
    "      \"last_name\": \"Berry\",\n" +
    "      \"position\": \"Chief Marketing Officer (CMO)\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"25th Jun 09\",\n" +
    "      \"salary\": \"$675,000\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Zenaida\",\n" +
    "      \"last_name\": \"Frank\",\n" +
    "      \"position\": \"Software Engineer\",\n" +
    "      \"office\": \"New York\",\n" +
    "      \"start_date\": \"4th Jan 10\",\n" +
    "      \"salary\": \"$125,250\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"first_name\": \"Zorita\",\n" +
    "      \"last_name\": \"Serrano\",\n" +
    "      \"position\": \"Software Engineer\",\n" +
    "      \"office\": \"San Francisco\",\n" +
    "      \"start_date\": \"1st Jun 12\",\n" +
    "      \"salary\": \"$115,000\"\n" +
    "    }\n" +
    "  ]";
    users = Arrays.asList(gson.fromJson(data, User[].class));
  }
}
