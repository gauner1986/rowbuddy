package nl.fontys.jee.rowbuddy.client.ui.trip.helper;

/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.Random;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;

/**
 * The data source for contact information used in the sample.
 */
public class TripDatabase {

  /**
   * Information about a contact.
   */
  public static class TripInfo implements Comparable<TripInfo> {

    /**
     * The key provider that provides the unique ID of a contact.
     */
    public static final ProvidesKey<TripInfo> KEY_PROVIDER = new ProvidesKey<
        TripInfo>() {
      public Object getKey(TripInfo item) {
        return item == null ? null : item.getId();
      }
    };

    private static int nextId = 0;

    private String routeName;
    private Date startDate;
    private Date endDate;
    private int rowerCount;
    private final int id;

    public TripInfo() {
      this.id = nextId;
      nextId++;
    }

    public int compareTo(TripInfo o) {
      return (o == null || o.endDate == null) ? -1 : -o.endDate.compareTo(
          endDate);
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof TripInfo) {
        return id == ((TripInfo) o).id;
      }
      return false;
    }
    
    public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public static int getNextId() {
		return nextId;
	}

	public int getRowerCount() {
		return rowerCount;
	}

	public void setRowerCount(int rowerCount) {
		this.rowerCount = rowerCount;
	}

	/**
     * @return the unique ID of the contact
     */
    public int getId() {
      return this.id;
    }

    @Override
    public int hashCode() {
      return id;
    }
  }

  private static final String[] LAST_NAMES = {
      "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller",
      "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White",
      "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark",
      "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young",
      "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams",
      "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts",
      "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins",
      "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan",
      "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox",
      "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James",
      "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood",
      "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell",
      "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler",
      "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell",
      "Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham",
      "Sullivan", "Wallace", "Woods", "Cole", "West", "Jordan", "Owens",
      "Reynolds", "Fisher", "Ellis", "Harrison", "Gibson", "Mcdonald", "Cruz",
      "Marshall", "Ortiz", "Gomez", "Murray", "Freeman", "Wells", "Webb",
      "Simpson", "Stevens", "Tucker", "Porter", "Hunter", "Hicks", "Crawford",
      "Henry", "Boyd", "Mason", "Morales", "Kennedy", "Warren", "Dixon",
      "Ramos", "Reyes", "Burns", "Gordon", "Shaw", "Holmes", "Rice",
      "Robertson", "Hunt", "Black", "Daniels", "Palmer", "Mills", "Nichols",
      "Grant", "Knight", "Ferguson", "Rose", "Stone", "Hawkins", "Dunn",
      "Perkins", "Hudson", "Spencer", "Gardner", "Stephens", "Payne", "Pierce",
      "Berry", "Matthews", "Arnold", "Wagner", "Willis", "Ray", "Watkins",
      "Olson", "Carroll", "Duncan", "Snyder", "Hart", "Cunningham", "Bradley",
      "Lane", "Andrews", "Ruiz", "Harper", "Fox", "Riley", "Armstrong",
      "Carpenter", "Weaver", "Greene", "Lawrence", "Elliott", "Chavez", "Sims",
      "Austin", "Peters", "Kelley", "Franklin", "Lawson"};

  /**
   * The singleton instance of the database.
   */
  private static TripDatabase instance;

  /**
   * Get the singleton instance of the contact database.
   *
   * @return the singleton instance
   */
  public static TripDatabase get() {
    if (instance == null) {
      instance = new TripDatabase();
    }
    return instance;
  }

  /**
   * The provider that holds the list of contacts in the database.
   */
  private ListDataProvider<TripInfo> dataProvider = new ListDataProvider<
      TripInfo>();


  /**
   * Construct a new contact database.
   */
  private TripDatabase() {
    // Generate initial data.
    generateTrips(250);
  }

  /**
   * Add a new contact.
   *
   * @param contact the contact to add.
   */
  public void addTrip(TripInfo contact) {
    List<TripInfo> contacts = dataProvider.getList();
    // Remove the contact first so we don't add a duplicate.
    contacts.remove(contact);
    contacts.add(contact);
  }

  /**
   * Add a display to the database. The current range of interest of the display
   * will be populated with data.
   *
   * @param display a {@Link HasData}.
   */
  public void addDataDisplay(HasData<TripInfo> display) {
    dataProvider.addDataDisplay(display);
  }

  /**
   * Generate the specified number of contacts and add them to the data
   * provider.
   *
   * @param count the number of contacts to generate.
   */
  public void generateTrips(int count) {
    List<TripInfo> contacts = dataProvider.getList();
    for (int i = 0; i < count; i++) {
      contacts.add(createTripInfo());
    }
  }


  /**
   * Refresh all displays.
   */
  public void refreshDisplays() {
    dataProvider.refresh();
  }

  /**
   * Create a new random {@link TripInfo}.
   *
   * @return the new {@link TripInfo}.
   */
  @SuppressWarnings("deprecation")
  private TripInfo createTripInfo() {
    TripInfo trip = new TripInfo();
    trip.setRouteName(nextValue(LAST_NAMES));
    

    // Create a year between 20-80 years ago.
    int year = (new Date()).getYear() - 21 - Random.nextInt(61);
    trip.setStartDate(
        new Date(year, Random.nextInt(12), 1 + Random.nextInt(31)));
    
 // Create a year between 20-80 years ago.
    year = (new Date()).getYear() - 21 - Random.nextInt(61);
    trip.setEndDate(
        new Date(year, Random.nextInt(12), 1 + Random.nextInt(31)));
    
    trip.setRowerCount(Random.nextInt(10));

    return trip;
  }

  /**
   * Get the next random value from an array.
   *
   * @param array the array
   * @return a random value in the array
   */
  private <T> T nextValue(T[] array) {
    return array[Random.nextInt(array.length)];
  }

}
