// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 19 -> sentence 19
 *                expressions, object-literals, functional-interface-lambda-literals -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: fun interface keeps SAM abstract member while inheriting default function body from interface
 */

// TESTCASE NUMBER: 1
fun interface Runner {
    fun run()
    fun label(): String = "run"
}

class PlainRunner : Runner {
    override fun run() {}
}

class CustomLabelRunner : Runner {
    override fun run() {}
    override fun label(): String = "custom"
}

fun invoke(r: Runner) {
    r.run()
}

fun box(): String {
    invoke { }
    if (PlainRunner().label() != "run") return "NOK: default-label"
    if (CustomLabelRunner().label() != "custom") return "NOK: override-label"
    val asRunner: Runner = PlainRunner()
    if (asRunner.label() != "run") return "NOK: via-interface"
    return "OK"
}
