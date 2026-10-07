class Solution {
    public String dayOfTheWeek(int day, int month, int year) {

        int[] daysInMonth = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        String[] weekDays = {
            "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"
        };

        long totalDays = 0;

        // Days from 1971 to the year before given year
        for (int i = 1971; i < year; i++) {
            totalDays += isLeapYear(i) ? 366 : 365;
        }

        // Days from January to the month before given month
        for (int i = 1; i < month; i++) {
            if (i == 2 && isLeapYear(year)) {
                totalDays += 29;
            } else {
                totalDays += daysInMonth[i - 1];
            }
        }

        // Days before the given day
        totalDays += day - 1;

        // Jan 1, 1971 was Friday
        return weekDays[(int)((totalDays + 4) % 7)];
    }

    private boolean isLeapYear(int year) {
        return year % 400 == 0 ||
               (year % 4 == 0 && year % 100 != 0);
    }
}