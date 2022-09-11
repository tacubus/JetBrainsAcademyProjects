package cinema;

public class Control {
    private Room    room;

    public boolean buildRoom ()
    {
        int rows        = View.askNumberOfRows();
        int seatsPerRow = View.askSeatsPerRow();

        room        = new Room(rows, seatsPerRow);

        return !room.isEmpty();
    }

    public void mainMenu ()
    {
        int menuOption;

        do {
            menuOption  = View.showMainMenu();

            switch (menuOption) {
                // Shows seats in the room
                case 1 -> showRoom();
                // Buys a ticket for the specified seat
                case 2 -> buyTicket();
                // Shows statics
                case 3 -> showStatics();
            }
        } while (menuOption != 0);
    }

    private boolean showRoom ()
    {
        if (!room.isEmpty())
        {
            View.showRoomSeats(room.getSeats());

            return true;
        }

        return false;
    }

    private int[] askSeatPosition () {
        int row             = View.askRowNumber();
        int seatNumberInRow = View.askSeatNumberInRow();

        return new int[]{row, seatNumberInRow};
    }

    private boolean showSeatPrice (int row, int seatNumberInRow) {
        int ticketPrice     = room.getSeatPrice(row, seatNumberInRow);

        if (ticketPrice > -1) {
            View.showSeatPrice(ticketPrice);

            return  true;
        }

        return false;
    }

    private void showErrorMessage (String message) {
        View.showErrorMessage(message);
    }

    private void buyTicket () {
        int[]   seatPosition;
        int     row;
        int     seatNumberInRow;
        boolean ticketBuyed     = false;

        do {
            seatPosition    = askSeatPosition();
            row             = seatPosition[0] - 1;
            seatNumberInRow = seatPosition[1] - 1;
            Room.Seat seat  = room.getSeat(row, seatNumberInRow);

            if (seat != null) {
                if (!seat.isBuyed()) {
                    seat.setBuyed();
                    ticketBuyed = true;
                } else {
                    showErrorMessage("\nThat ticket has already been purchased!");
                }
            } else {
                showErrorMessage("\nWrong input!");
            }
        } while (!ticketBuyed);

        showSeatPrice(row, seatNumberInRow);
    }

    private void showStatics ()
    {
        int[]   purchasedTickets            = room.getPurchasedTickets();

        View.showStatistics(purchasedTickets[0],
                (double) (purchasedTickets[0] * 100) / room.getTotalSeats(),
                purchasedTickets[1],
                room.getTotalProfit());
    }
}
