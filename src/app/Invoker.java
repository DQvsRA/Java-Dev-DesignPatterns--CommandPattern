package app;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import app.commands.ICommand;
import app.entities.Unit;
import app.enums.MoveType;
import app.enums.ProcessCommand;

public final class Invoker 
{
	private static final Invoker instance = new Invoker();
	
	private final Map<MoveType, Class<?>> _unitMoveCommands = new HashMap<MoveType, Class<?>>();
	private final Map<ProcessCommand, ICommand> _processCommands = new HashMap<ProcessCommand, ICommand>();
	
	private final List<ICommand> _movesHistory = new ArrayList<ICommand>();
	private final List<ICommand> _commandsQueue = new ArrayList<ICommand>();
	
	private ICommand _currentMoveCommand;
	private int _moveHistoryCurrentCommandIndex;
	
	private Invoker() { }
	
	public static Invoker getInstance() {
        return instance;
    }
	
	public void registerMoveCommand(MoveType moveType, Class<?> classRef) {
		_unitMoveCommands.put(moveType, classRef);
	}
	
	public void registerProcessCommand(ProcessCommand processKey, ICommand command) {
		_processCommands.put(processKey, command);
	}
	
	public void storeCommandToHistory(ICommand command) {
		if(_movesHistory.contains(command) == false) {
			if(nextHistoryCommandExist()) {
				clearMoveHistoryFromNextCommand();
			}
			_movesHistory.add(command);
			_moveHistoryCurrentCommandIndex = _movesHistory.size() - 1;
			System.out.println("storeCommandToHistory : " + _moveHistoryCurrentCommandIndex + " | " + command );
		}
	}
	
	public void clearMoveHistoryFromNextCommand() {
		_moveHistoryCurrentCommandIndex += 1;
		while(_movesHistory.size() > _moveHistoryCurrentCommandIndex)
			_movesHistory.remove(_moveHistoryCurrentCommandIndex);
	}
	
	public boolean previousHistoryCommandExist() {
		return _moveHistoryCurrentCommandIndex > 0;
	}
	
	public boolean nextHistoryCommandExist() {
		System.out.println("getMoveHistoryFromCurrentCommand : " + _moveHistoryCurrentCommandIndex + " | " + (_movesHistory.size() - 1) );
		return _moveHistoryCurrentCommandIndex < (_movesHistory.size() - 1);
	}
	
	public ICommand getPreviousMoveHistoryCommand() {
		_moveHistoryCurrentCommandIndex--;
		_currentMoveCommand = _movesHistory.get(_moveHistoryCurrentCommandIndex);
		return _currentMoveCommand;
	}
	
	public ICommand getNextMoveHistoryCommand() {
		_moveHistoryCurrentCommandIndex++;
		_currentMoveCommand = _movesHistory.get(_moveHistoryCurrentCommandIndex);
		return _currentMoveCommand;
	}
	
	public List<ICommand> getMoveHistoryFromCurrentCommand() {
		int historySize = _movesHistory.size() - 1;
		List<ICommand> result = new ArrayList<ICommand>();
		while(_moveHistoryCurrentCommandIndex++ < historySize) {
			result.add(_movesHistory.get(_moveHistoryCurrentCommandIndex));
		}
		System.out.println("getMoveHistoryFromCurrentCommand : " + _moveHistoryCurrentCommandIndex + " | " + result.size() );
		_moveHistoryCurrentCommandIndex = _movesHistory.size() - 1;
		return result;
	}
	
	public int getMoveHistoryCurrentCommandIndex() {
		return _moveHistoryCurrentCommandIndex;
	}
	
	public ICommand getProcessCommand(ProcessCommand processKey) {
		return _processCommands.get(processKey);
	}
	
	public void executeProcessCommand(ProcessCommand processKey, Unit unit) {
		if(_processCommands.containsKey(processKey)) _processCommands.get(processKey).execute(unit);
	}
	
	public void executeProcessCommand(ProcessCommand processKey, Unit unit, Point position) {
		if(_processCommands.containsKey(processKey)) _processCommands.get(processKey).execute(unit, position);
	}
	
	public void executeProcessCommand(ProcessCommand processKey) {
		if(_processCommands.containsKey(processKey)) _processCommands.get(processKey).execute();
	}
	
	public void executeProcessCommand(ProcessCommand processKey, MoveType moveType) {
		if(_processCommands.containsKey(processKey)) _processCommands.get(processKey).execute(moveType);
	}
	
	public void executeProcessCommandWithAnotherCommand(ProcessCommand processKey, ICommand command) {
		if(_processCommands.containsKey(processKey)) {
			_processCommands.get(processKey).execute(command);
			//System.out.println("executeProcessCommandWithAnotherCommand : " + _processCommands.get(processKey));
		}
	}

	public Class<?> findMoveCommandClass(MoveType moveType) {
		return _unitMoveCommands.get(moveType);
	}
	
	public boolean hasQueueMoveCommand() {
		return _commandsQueue.size() > 0;
	}

	public void queueMoveCommand(ICommand command) {
		_commandsQueue.add(command);
	}
	
	public void clearMoveCommandQueue() {
		_commandsQueue.clear();
	}

	public ICommand getNextQueueMoveCommand() {
		return _commandsQueue.remove(0);
	}
	
	public void goThroughMovesHistory(Consumer<ICommand> action) {
		_movesHistory.forEach(action);
	}
	
	public ICommand getCurrentMoveCommand() {
		return _currentMoveCommand;
	}
	
	public void setCurrentMoveCommand(ICommand command) {
		if(_movesHistory.contains(command)) _moveHistoryCurrentCommandIndex = _movesHistory.indexOf(command);
		_currentMoveCommand = command;
	}
}
