compile:
	javac -d compiled/ $$(find . -name '*.java')
	javadoc -d doc/ $$(find . -name '*.java')
	cd compiled; rm -rf out; mkdir out; cp ../src/visualizer/VisualizerView.fxml ./visualizer/VisualizerView.fxml; cp ../src/simulator/SimulatorView.fxml ./simulator/SimulatorView.fxml

assemble1:
	cd compiled ; java assembler.Main        ../data/code_01.as
assemble2:
	cd compiled ; java assembler.Main        ../data/all.as

visualize1:
	cd compiled ; java visualizer.Main      ../data/code_01.o
visualize2:
	cd compiled ; java visualizer.Main       ../data/all.o

simulate1:
	cd compiled ; java simulator.Main        ../data/code_01.as
simulate2:
	cd compiled ; java simulator.Main        ../data/all.as

simulateNoisy1:
	cd compiled ; java simulator.Main        ../data/code_01.as noisy
simulateNoisy2:
	cd compiled ; java simulator.Main        ../data/all.as noisy