package funsets


/**
 * 2. Purely Functional Sets.
 */
object FunSets {
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   */
  type Set = Int => Boolean

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
   * Returns the set of the one given element.
   */
    def singletonSet(elem: Int): Set =
      (i: Int) => { elem == i }
  

  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
    def union(s: Set, t: Set): Set =
      (i: Int) => { s(i) || t(i) }
  
  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
    def intersect(s: Set, t: Set): Set =
      (i: Int) => { s(i) && t(i) }
  
  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
    def diff(s: Set, t: Set): Set =
      (i: Int) => { s(i) && !t(i) }
  
  /**
   * Returns the subset of `s` for which `p` holds.
   */
    def filter(s: Set, p: Int => Boolean): Set =
      (i: Int) => { s(i) && p(i) }
  

  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
    def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > 1000) true
      else if (contains(s, a) && !p(a)) false
      else iter(a + 1)
    }
    iter(-1000)
  }
  
  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
    def exists(s: Set, p: Int => Boolean): Boolean = {
      def iter(a: Int): Boolean = {
        if (a > 1000) false
        else if (contains(s, a) && p(a)) true
        else iter(a + 1)
      }
      iter(-1000)
    }
  
  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
   // This is so gross... is there a better way?
   // Seems to require function inversion in the general case?
    def map(s: Set, f: Int => Int): Set = {
      def iter(a: Int, set: Set): Set = {
        if (a > 1000) set
        else if (contains(s, a)) union(union(set, singletonSet(f(a))), iter(a+1, set))
        else iter(a + 1, set)
      }

      iter(-1000, (i: Int) => { false })
    }
  
  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }
}
