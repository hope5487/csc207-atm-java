This is a simulation of an Automated Teller Machine (ATM) that allows a user to pay bills, deposit, withdraw, and
transfer funds. As a user you will interact with the ATM through input (keyboard).

The following is documentation of the ATM.

When the program starts:
You will be introduced to the start of the program and prompted to enter your credentials.
There are 3 ways to login and make the ATM run a certain way, the three are below and explained:

Note: When the ATM's time reaches midnight the program update the date.
Note: When the end of the month is reached your savings account will incur by a factor of 0.1.

1) Logging in as the Bank Manager
If you enter BM for both username and password, you will be authenticated as the BankManager,
this is for when a user wants to create and account, a new user, undo the most recent transaction of an account, or
restock the bills in the ATM. If any option is clicked on follow and enter the appropriate input for the prompts.
In real life a person would meet at a bank and request for a new account or login so we designed it for the BankManager
to do, there is no option in the user menu. Note, when a new user is created that user will only have a chequing account
available to start and will be able to add any account in the future.

The BankManager will be alerted through the alerts.txt file as well as indications of
low bills when any user transactions occur. Can be thought of as, the User interacts with the ATM and the ATM
talks to the BankManager.

2)Logging in as a User that does not exist
If you enter a login and are not in the system you will be notified you are not in the system. The system will
create a login for you, using the username you entered and a temporary password that is 0000. You will then be
authenticated and added to the system (list of users).

From here you will have access to the entire system, outlined below when you login as a valid user, continue to 3

3)Logging in as a valid user
As a valid user you will be brought to the main menu that allows you to pay bills, transfer funds, withdraw, deposit,
or manage your account(s).

    1. Paying Bills: When you want to pay bills a list of your accounts and their names will be displayed. Then following
    the prompts enter the name of the account you want to pay bills for, the name of the recipient
    (who you are paying bills to, third party), and the amount that has to paid.

    2. Transferring Funds: When you want to transfer funds you are given two options, between your accounts, and to another
    user.
        Between Accounts:
        When you want to transfer funds between accounts, you will be given the list of your accounts. Then following
        the prompts enter the name of the account you are transferring funds FROM, the account name of the account you want
        to transfer funds INTO, and the transfer amount. Note: if you need another account, the BankManager can create a new
        account for you.

        To Another User:
        When you want to transfer funds to another user, you will be given a list of all valid users in the system. Then
        following the prompts enter the name of the user you want to send funds to, the name of your account you want to
        transfer funds from and the transfer amount.

    3. Withdrawing Funds: When you want to withdraw funds, you will be given a list of your accounts you want to withdraw
    funds from. Following the prompts, you will enter the name of the account you want to withdrawal from, the amount
    of funds to withdraw ($20, $50, $100, $200, or $500), and the bill type you want the funds in, 5, 10, 20, 50, or 100
    If there are not funds in the specified account or enough of the bill types specified you will notified by the system

    4. Depositing Funds: When you want to deposit funds, you will be given a list of your accounts you want to deposit
    into. Following the prompts, enter the name of the account you want to deposit into and the amount you want to
    deposit, notes, only multiples of $5 will be accepted, you will be notified by the system otherwise, and your
    primary chequing account will be the account that the funds will be deposited into.

    Note: For the above operations, if you want a new account type, the BankManager will be able to create a new account
    for you.

    5. Managing your Account(s): When you want to manage your account or accounts, you can view your account info or change
    your password for your credentials.

        Account Info: When viewing account info you can select:
            Account Summary: A summary of all accounts which provides all the balances of each account you have.

            Recent Transactions: From the list of your accounts, select which account you want to view the most recent
            transaction. Note: If you want to undo any of these transactions the BankManager will be able to do so.

            Check Date of Creation: From the list of your accounts, select which account you want view the date that
            it was created.

            Net Total: Your debt account balance subtracted from your asset account balance

            Set Primary Account: Your chequing account is very valuable and can be used for all operations. You can
            create multiple chequing accounts, and in this option from all of the chequing accounts you have, you can
            set or change which chequing account you want to be the primary chequing account. The primary account will be
            the default destination for deposits.

        Change Password: When you want to change your password for your login. Follow the prompts, enter your new password
        and it will be saved by the system for future use