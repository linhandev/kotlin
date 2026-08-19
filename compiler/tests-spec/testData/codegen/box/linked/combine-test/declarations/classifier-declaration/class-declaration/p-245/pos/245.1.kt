// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 245 -> sentence 245
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 245 -> sentence 245
 *                inheritance, inheriting -> paragraph 245 -> sentence 245
 * NUMBER: 1
 * DESCRIPTION: a class may implement two independent generic interfaces with different type arguments and expose each member through its interface view; contrasts with p-244 multi-parameter single interface and p-242 shared type-parameter inheritance
 */

// TESTCASE NUMBER: 1
interface A<T> {
    fun ta(): T
}

interface B<U> {
    fun tb(): U
}

class IntStringBoth : A<Int>, B<String> {
    override fun ta(): Int = 1
    override fun tb(): String = "s"
}

// TESTCASE NUMBER: 2
interface Left<T> {
    val left: T
}

interface Right<U> {
    val right: U
}

class BoolLongBoth : Left<Boolean>, Right<Long> {
    override val left: Boolean = true
    override val right: Long = 9L
}

// TESTCASE NUMBER: 3
interface Producer<T> {
    fun produce(): T
}

interface Consumer<U> {
    fun accept(x: U): U
}

class MixBoth : Producer<String>, Consumer<Int> {
    override fun produce(): String = "p"
    override fun accept(x: Int): Int = x + 1
}

fun box(): String {
    if (IntStringBoth().ta() != 1) return "NOK: ta"
    if (IntStringBoth().tb() != "s") return "NOK: tb"
    val asA: A<Int> = IntStringBoth()
    val asB: B<String> = IntStringBoth()
    if (asA.ta() != 1 || asB.tb() != "s") return "NOK: via-a-b"

    if (!BoolLongBoth().left) return "NOK: left"
    if (BoolLongBoth().right != 9L) return "NOK: right"
    val asLeft: Left<Boolean> = BoolLongBoth()
    val asRight: Right<Long> = BoolLongBoth()
    if (!asLeft.left || asRight.right != 9L) return "NOK: via-left-right"

    if (MixBoth().produce() != "p") return "NOK: produce"
    if (MixBoth().accept(2) != 3) return "NOK: accept"
    val asProducer: Producer<String> = MixBoth()
    val asConsumer: Consumer<Int> = MixBoth()
    if (asProducer.produce() != "p" || asConsumer.accept(4) != 5) return "NOK: via-mix"
    return "OK"
}
