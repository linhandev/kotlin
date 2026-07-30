// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 256 -> sentence 256
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 256 -> sentence 256
 *                inheritance, inheriting -> paragraph 256 -> sentence 256
 *                inheritance, overriding -> paragraph 256 -> sentence 256
 * NUMBER: 1
 * DESCRIPTION: a single override can satisfy identically named members from two generic interfaces with the same type argument; contrasts with p-194/p-219 non-generic dual interfaces, p-245 different member names/type args, and next-point incompatible dual returns
 */

// TESTCASE NUMBER: 1
interface A<T> {
    fun f(): T
}

interface B<T> {
    fun f(): T
}

class C : A<Int>, B<Int> {
    override fun f(): Int = 1
}

// TESTCASE NUMBER: 2
interface LeftText<T> {
    fun text(): T
}

interface RightText<T> {
    fun text(): T
}

class StringBoth : LeftText<String>, RightText<String> {
    override fun text(): String = "ok"
}

// TESTCASE NUMBER: 3
interface LeftVal<T> {
    val n: T
}

interface RightVal<T> {
    val n: T
}

class BoolBoth : LeftVal<Boolean>, RightVal<Boolean> {
    override val n: Boolean = true
}

fun box(): String {
    if (C().f() != 1) return "NOK: c-f"
    val asA: A<Int> = C()
    if (asA.f() != 1) return "NOK: via-a"
    val asB: B<Int> = C()
    if (asB.f() != 1) return "NOK: via-b"

    if (StringBoth().text() != "ok") return "NOK: text"
    val asLeft: LeftText<String> = StringBoth()
    if (asLeft.text() != "ok") return "NOK: via-left-text"
    val asRight: RightText<String> = StringBoth()
    if (asRight.text() != "ok") return "NOK: via-right-text"

    if (BoolBoth().n != true) return "NOK: bool"
    val asLeftVal: LeftVal<Boolean> = BoolBoth()
    if (asLeftVal.n != true) return "NOK: via-left-val"
    val asRightVal: RightVal<Boolean> = BoolBoth()
    if (asRightVal.n != true) return "NOK: via-right-val"
    return "OK"
}
