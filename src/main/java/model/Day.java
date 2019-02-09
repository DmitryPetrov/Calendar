package model;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Day {
	private boolean useful = false;
	private boolean work = false;
	private boolean study = false;
	private boolean learnLanguag = false;
	private boolean sport = false;
	private boolean alcohol = false;
	private boolean smoke = false;
	private Mood mood = Mood.NORMAL;
	private Calendar date;
	
	public Day() {
        setDate(new GregorianCalendar());
	}
	
	public Day(Mood mood) {
		this.mood = mood;
		setDate(new GregorianCalendar());
	}
	
	public Day(Calendar date) {
	    setDate(date);
	}

	public boolean getUseful() {
		return useful;
	}

	public void setUseful(boolean useful) {
		this.useful = useful;
	}

	public boolean getWork() {
		return work;
	}

	public void setWork(boolean work) {
		this.work = work;
	}

	public boolean getStudy() {
		return study;
	}

	public void setStudy(boolean learn) {
		this.study = learn;
	}

	public boolean getLearnLanguag() {
		return learnLanguag;
	}

	public void setLearnLanguag(boolean learnLanguag) {
		this.learnLanguag = learnLanguag;
	}

	public boolean getSport() {
		return sport;
	}

	public void setSport(boolean sport) {
		this.sport = sport;
	}

	public boolean getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(boolean alcohol) {
		this.alcohol = alcohol;
	}

	public boolean getSmoke() {
		return smoke;
	}

	public void setSmoke(boolean smoke) {
		this.smoke = smoke;
	}

	public Mood getMood() {
		return mood;
	}

	public void setMood(Mood mood) {
		this.mood = mood;
	}
	
    public void setMood(String mood) {
        this.mood = Mood.valueOf(mood.toUpperCase());
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }   
    
    //need for insert to database.
    public String getDateString() {
        return dateToStringForDataBase();
    }
	
    //need for insert to database.
    //GregorianCalendar.Class starts month of year from 0
    private String dateToStringForDataBase() {
        return date.get(Calendar.YEAR) + "-"
                + (1 + date.get(Calendar.MONTH) ) + "-"
                + date.get(Calendar.DAY_OF_MONTH);
    }
    
    //need for insert to database
    public String getMoodString() {
        return mood.toString().toLowerCase();
    }

    //need for insert to database. 
    //MySql can't understand 'true' and 'false', need 0 and 1 
    public int getUsefulInt() {
        if(useful) {
            return 1;
        }
        return 0;       
     }
    
    //need for insert to database. 
    //MySql can't understand 'true' and 'false', need 0 and 1 
    public int getWorkInt() {
        if(work) {
            return 1;
        }
        return 0;       
     }
    
    //need for insert to database. 
    //MySql can't understand 'true' and 'false', need 0 and 1 
    public int getStudyInt() {
        if(study) {
            return 1;
        }
        return 0;       
     }
    
    //need for insert to database. 
    //MySql can't understand 'true' and 'false', need 0 and 1 
    public int getLearnLanguagInt() {
        if(learnLanguag) {
            return 1;
        }
        return 0;       
     }
    
    //need for insert to database. 
    //MySql can't understand 'true' and 'false', need 0 and 1     
    public int getSportInt() {
        if(sport) {
            return 1;
        }
        return 0;       
    }
   
    //need for insert to database. 
    //MySql can't understand 'true' and 'false', need 0 and 1     
    public int getAlcoholInt() {
        if(alcohol) {
            return 1;
        }
        return 0;       
     }
    
    //need for insert to database. 
    //MySql can't understand 'true' and 'false', need 0 and 1     
    public int getSmokeInt() {
        if(smoke) {
            return 1;
        }
        return 0;       
     }
    
    public String getDayOfWeek() {
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        
        switch(dayOfWeek) {
        case 1: 
            return "monday";
        case 2: 
            return "tuesday";
        case 3: 
            return "wednesday";
        case 4: 
            return "thursday";
        case 5: 
            return "friday";
        case 6: 
            return "saturday";    
        case 7: 
            return "sunday";
        default:
            return "day.getDayOfWeek ERROR";
        }
    }
    
    public String toString() {
        String day =  "DAY: date=" + getDateString() + ";"
                    + " dayOfWeek=" + getDayOfWeek() + ";"
                    + " mood=" + mood.toString() + ";"
                    + " useful=" + useful  + ";"
                    + " work=" + work + ";"
                    + " study=" + study + ";"
                    + " learnLanguag=" + learnLanguag + ";"
                    + " sport=" + sport + ";"
                    + " alcohol=" + alcohol + ";"
                    + " smoke=" + smoke;
        
        return day;
    }
    
    public String dayToStringForDataBase() {
        return "'" + this.getDateString() + "', '"
                + this.getMoodString().toLowerCase() + "', "
                + this.getUsefulInt() + ", " 
                + this.getWorkInt() + ", "
                + this.getStudyInt() + ", "
                + this.getLearnLanguagInt() + ", "
                + this.getSportInt() + ", "
                + this.getAlcoholInt() + ", "
                + this.getSmokeInt();
    }
}
