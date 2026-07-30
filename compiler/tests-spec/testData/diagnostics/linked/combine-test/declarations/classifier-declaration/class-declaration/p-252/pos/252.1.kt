// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 252 -> sentence 252
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 252 -> sentence 252
 *                expressions, object-literals, functional-interface-lambda-literals -> paragraph 252 -> sentence 252
 *                inheritance, inheriting -> paragraph 252 -> sentence 252
 * NUMBER: 1
 * DESCRIPTION: precise types for a generic fun interface constructed via SAM lambda with a fixed type argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun interface Runner<T> {
    fun run(t: T): T
}

class IntRunner : Runner<Int> {
    override fun run(t: Int): Int = t
}

fun case1() {
    val r = Runner<Int> { it }
    r checkType { check<Runner<Int>>() }
    r.run(1) checkType { check<Int>() }
    val asRunner: Runner<Int> = Runner { it * 2 }
    asRunner.run(3) checkType { check<Int>() }
    IntRunner().run(9) checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun interface Mapper<T> {
    fun map(t: T): String
}

class IntMapper : Mapper<Int> {
    override fun map(t: Int): String = t.toString()
}

fun case2() {
    val m = Mapper<Int> { it.toString() }
    m checkType { check<Mapper<Int>>() }
    m.map(7) checkType { check<String>() }
    IntMapper().map(7) checkType { check<String>() }
}

// TESTCASE NUMBER: 3
fun interface Predicate<T> {
    fun test(t: T): Boolean
}

class PositivePred : Predicate<Int> {
    override fun test(t: Int): Boolean = t > 0
}

fun case3() {
    val p = Predicate<String> { it.isEmpty() }
    p checkType { check<Predicate<String>>() }
    p.test("x") checkType { check<Boolean>() }
    PositivePred().test(1) checkType { check<Boolean>() }
}
