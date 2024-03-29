package com.gradle.enterprise.update

import groovy.transform.ToString
import groovy.transform.TupleConstructor

@TupleConstructor
@ToString
class CommitJobReport {
    int completed
    SummaryResult summaryResult
    List<CommitJob> commits

    boolean isJobCompleted() {
        completed == commits.size()
    }

    String toDisplayable() {
        def builder = new StringBuilder()
        def rowUtil = new DisplayRow(30, 0, builder)

        builder << 'Pull request status'.center(80, '-')
        builder << '\n'
        rowUtil.row('Job completed', isJobCompleted() ? 'Yes' : 'No')
        builder << '-'.center(80, '-')
        builder << '\n'
        commits.stream().forEach {
            rowUtil.row(it.state, it.resultLink)
        }
        builder.toString()
    }
}
