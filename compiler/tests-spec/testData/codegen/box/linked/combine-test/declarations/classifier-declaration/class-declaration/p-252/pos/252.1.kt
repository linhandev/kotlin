// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 252 -> sentence 252
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 252 -> sentence 252
 *                expressions, object-literals, functional-interface-lambda-literals -> paragraph 252 -> sentence 252
 *                inheritance, inheriting -> paragraph 252 -> sentence 252
 * NUMBER: 1
 * DESCRIPTION: a fun interface may declare a type parameter and be constructed via SAM lambda with a fixed type argument; contrasts with interface-declaration p-19 non-generic fun interface and with p-231 non-fun generic interface producers
 */

// TESTCASE NUMBER: 1
fun interface Runner<T> {
    fun run(t: T): T
}

class IntRunner : Runner<Int> {
    override fun run(t: Int): Int = t
}

// TESTCASE NUMBER: 2
fun interface Mapper<T> {
    fun map(t: T): String
}

class IntMapper : Mapper<Int> {
    override fun map(t: Int): String = t.toString()
}

// TESTCASE NUMBER: 3
fun interface Predicate<T> {
    fun test(t: T): Boolean
}

class PositivePred : Predicate<Int> {
    override fun test(t: Int): Boolean = t > 0
}

fun box(): String {
    if (Runner<Int> { it }.run(1) != 1) return "NOK: runner-int"
    if (Runner<String> { it.uppercase() }.run("ab") != "AB") return "NOK: runner-string"
    val asRunner: Runner<Int> = Runner { it * 2 }
    if (asRunner.run(3) != 6) return "NOK: via-runner"
    if (IntRunner().run(9) != 9) return "NOK: class-runner"

    if (Mapper<Int> { it.toString() }.map(7) != "7") return "NOK: mapper-int"
    if (Mapper<Boolean> { if (it) "t" else "f" }.map(true) != "t") return "NOK: mapper-bool"
    val asMapper: Mapper<Int> = Mapper { "n=$it" }
    if (asMapper.map(2) != "n=2") return "NOK: via-mapper"
    if (IntMapper().map(7) != "7") return "NOK: class-mapper"

    if (!Predicate<Int> { it > 0 }.test(1)) return "NOK: pred-true"
    if (Predicate<String> { it.isEmpty() }.test("x")) return "NOK: pred-false"
    val asPred: Predicate<Int> = Predicate { it % 2 == 0 }
    if (!asPred.test(4)) return "NOK: via-pred"
    if (!PositivePred().test(1)) return "NOK: class-pred"
    return "OK"
}
