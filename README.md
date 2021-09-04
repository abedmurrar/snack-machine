# Snack Machine

### Basic Flow
1. This use case begins when the customer wants to purchase snacks.
2. The customer selects a number by pressing on the keypad.
3. The VM displays a message that the snack is available for the selected number and displays its price.
4. The customer inserts the money.
5. The VM validates the money.
6. The VM accepts the money.
7. The VM displays the accumulated amount of money each time a new money is entered.
8. The VM monitors the amount of the accepted money, If the money is enough, the VM dispenses the selected snack to the customer.
9. The VM determines if any change should be sent back to customer.
10. The VM displays the change at panel.
11. Then, the VM dispenses change.

### Alternative Flow 1
1. This use case begins when the customer wants to purchase snacks.
2. The customer selects a number by pressing on the keypad.
3. The VM displays a message that the snack is available for the selected number and displays its price.
4. The customer inserts the money.
5. The VM validates the money.
6. The VM does not accept money
7. The VM returns money to customer waiting for other money to be inserted.

### Alternative Flow 2
1. This use case begins when the customer wants to purchase snacks.
2. The customer selects a number by pressing on the keypad.
3. The VM displays a message that the snack is available for the selected number and displays its price.
4. The customer inserts the money.
5. The VM validates the money.
6. The VM accepts the money.
7. The VM displays the accumulated amount of money each time a new money is entered.
8. The VM monitors the amount of the accepted money, If the money is enough, the VM dispenses the selected snack to the customer.
9. The VM determines if any change should be sent back to customer.
10. The VM does not have enough change
11. The VM returns inserted money
