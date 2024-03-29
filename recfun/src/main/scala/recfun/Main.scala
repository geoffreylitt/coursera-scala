package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int =
      if (r == 0) 1 else {
        if (c == 0 || c == r) 1 else {
          pascal(c - 1, r - 1) + pascal(c, r - 1)
        }
      }

  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean =
      balanceWithAcc(chars, 0)

    def balanceWithAcc(chars: List[Char], acc: Int): Boolean =
      if (chars.isEmpty) {
        acc == 0
      } else {
        chars.head match {
          case '(' => balanceWithAcc(chars.tail, acc + 1)
          case ')' => if (acc <= 0) false else balanceWithAcc(chars.tail, acc - 1)
          case _ => balanceWithAcc(chars.tail, acc)
        }
      }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int =
      if (coins.isEmpty) 0
      else if (money == 0) 1
      else if (money < 0) 0
      else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
