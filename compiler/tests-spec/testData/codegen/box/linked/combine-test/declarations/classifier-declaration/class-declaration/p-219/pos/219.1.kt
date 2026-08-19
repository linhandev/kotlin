// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 219 -> sentence 219
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 219 -> sentence 219
 *                inheritance, overriding -> paragraph 219 -> sentence 219
 * NUMBER: 1
 * DESCRIPTION: a single covariant override return type can satisfy two interfaces declaring the same more general return type; contrasts with p-194 same-exact return, p-58 class-base covariance, and next-point incompatible dual returns
 */

// TESTCASE NUMBER: 1
interface LeftCreate {
    fun create(): Number
}

interface RightCreate {
    fun create(): Number
}

class IntCreate : LeftCreate, RightCreate {
    override fun create(): Int = 1
}

// TESTCASE NUMBER: 2
interface LeftText {
    fun text(): CharSequence
}

interface RightText {
    fun text(): CharSequence
}

class StringText : LeftText, RightText {
    override fun text(): String = "ok"
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Number
}

interface RightVal {
    val n: Number
}

class IntVal : LeftVal, RightVal {
    override val n: Int = 7
}

fun box(): String {
    if (IntCreate().create() != 1) return "NOK: create"
    val asLeftCreate: LeftCreate = IntCreate()
    if (asLeftCreate.create() != 1) return "NOK: via-left-create"
    val asRightCreate: RightCreate = IntCreate()
    if (asRightCreate.create() != 1) return "NOK: via-right-create"
    if (asLeftCreate.create() !is Int) return "NOK: create-is-int"

    if (StringText().text() != "ok") return "NOK: text"
    val asLeftText: LeftText = StringText()
    if (asLeftText.text().toString() != "ok") return "NOK: via-left-text"
    val asRightText: RightText = StringText()
    if (asRightText.text().toString() != "ok") return "NOK: via-right-text"
    if (asLeftText.text() !is String) return "NOK: text-is-string"

    if (IntVal().n != 7) return "NOK: val"
    val asLeftVal: LeftVal = IntVal()
    if (asLeftVal.n != 7) return "NOK: via-left-val"
    val asRightVal: RightVal = IntVal()
    if (asRightVal.n != 7) return "NOK: via-right-val"
    if (asLeftVal.n !is Int) return "NOK: val-is-int"
    return "OK"
}
