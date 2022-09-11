package cinema;

import java.util.ArrayList;

public class Room {
    private ArrayList<ArrayList<Seat>>  seats;
    private final int                   rows;
    private final int                   seatsPerRow;

    public Room (int rows, int seatsPerRow) {
        this.rows           = rows;
        this.seatsPerRow    = seatsPerRow;

        if (!buildRoom()) {
            seats   = null;
        }
    }

    private boolean buildRoom () {
        seats   = new ArrayList<>();

        if (rows > 0 && seatsPerRow > 0) {
            for (int i = 0; i < rows; i++) {
                ArrayList<Seat> row = new ArrayList<>();

                for (int j = 0; j < seatsPerRow; j++) {
                    row.add(new Seat(calculateSeatPrice(i)));
                }

                seats.add(row);
            }

            return true;
        }

        return false;
    }

    public boolean isEmpty ()
    {
        return seats == null;
    }

    private int calculateSeatPrice (int seatRow) {
        if (rows * seatsPerRow > 60) {
            if (seatRow >= rows / 2)
            {
                return 8;
            }
        }

        return 10;
    }

    public int getSeatPrice (int row, int seatInRow) {
        Seat seat   = getSeat(row, seatInRow);

        if (seat != null) {
            return seat.getPrice();
        }

        return -1;
    }

    public ArrayList<ArrayList<Seat>> getSeats ()
    {
        return seats;
    }

    public Seat getSeat (int row, int seatInRow) {
        if ((row > -1 && seatInRow > -1) && (row < rows && seatInRow < seatsPerRow)) {
            return seats.get(row).get(seatInRow);
        }

        return null;
    }

    public int[] getPurchasedTickets () {
        int purchasedTickets    = 0;
        int currentIncome       = 0;

        for (ArrayList<Seat> row : seats) {
            for (Seat seat : row) {
                if (seat.isBuyed()) {
                    ++purchasedTickets;

                    currentIncome   += seat.getPrice();
                }
            }
        }

        return new int[] {purchasedTickets, currentIncome};
    }

    public int getTotalSeats ()
    {
        return rows * seatsPerRow;
    }

    /**
     * Calculate total profit of the room
     *
     * @return int
     */
    public int getTotalProfit () {
        int totalSeats  = rows * seatsPerRow;

        if (totalSeats > 60) {
            int frontSeats  = (rows / 2) * seatsPerRow;
            int backSeats   = totalSeats - frontSeats;

            return (backSeats * 8) + (frontSeats * 10);
        } else {
            return totalSeats * 10;
        }
    }

    public class Seat {
        private int price;
        private boolean buyed   = false;

        public Seat (int price) {
            this.price  = price;
            this.buyed  = false;
        }

        public void setBuyed ()
        {
            this.buyed = true;
        }

        public int getPrice () {
            return price;
        }

        public boolean isBuyed() {
            return buyed;
        }
    }
}
